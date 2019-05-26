package ma.jwt.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.jwt.entities.AppUSer;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	AppUSer app = null;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			// ObjectMapper if data sent in json format else use getparameter if data sent in url username=xxx&pass=xxx
				 //String username = request.getParameter("username"); // same code for passord
			app = new ObjectMapper().readValue(request.getInputStream(),AppUSer.class); 
			
			
		} catch (Exception e) { throw new RuntimeException(e); }
		System.out.println("************************ee*****************");
		System.out.println("*************"+app.getUsename()+"********");
		System.out.println("*************"+app.getPassword()+"*******");
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(app.getUsename(),app.getPassword()));
	}
	
	@Override
	// FilterChain filter of Spring Securitt
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User springUser =(User) authResult.getPrincipal();
		String jwtToken = Jwts.builder().setSubject(springUser.getUsername())
		.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
		.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
		.claim("roles",springUser.getAuthorities())
		.compact();
		response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+jwtToken);
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
