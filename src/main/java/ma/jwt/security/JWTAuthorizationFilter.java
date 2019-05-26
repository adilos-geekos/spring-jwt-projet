package ma.jwt.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		  res.addHeader("Access-Control-Allow-Origin", "*");
	  res.addHeader("Access-Control-Allow-Headers","Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		  res.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Authorization");
		  if(request.getMethod().equals("OPTIONS")){
		   res.setStatus(HttpServletResponse.SC_OK);
		  }

		  
		String jwt  = request.getHeader("Authorization");
		System.out.println("***********c*************"+request.getHeader("Authorization")+"*********************");
		if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			System.out.println("jwt null");
			filterChain.doFilter(request, res); return;
		}
		Claims claims = Jwts.parser()
						.setSigningKey(SecurityConstants.SECRET)
						.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
						.getBody();
		String username = claims.getSubject();
		ArrayList<Map<String,String>> roles = (ArrayList<Map<String,String>>) claims.get("roles");
		Collection<GrantedAuthority> authoroties = new ArrayList<>();
		roles.forEach(t->{authoroties.add(new SimpleGrantedAuthority(t.get("authority")));});
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authoroties);
		// context security de spring this set will charge utilisateur authentifie
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		super.doFilter(request, res, filterChain);
		  }

}
