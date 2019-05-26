package ma.jwt.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class AppUSer {
	@Id @GeneratedValue
	private Long id;
	@Column(unique=true)
	private String usename;
	@JsonIgnore
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRoles> roles = new ArrayList<>();
	public AppUSer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppUSer(String usename, String password, Collection<AppRoles> roles) {
		super();
		this.usename = usename;
		this.password = password;
		this.roles = roles;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<AppRoles> getRoles() {
		return roles;
	}
	public void setRoles(Collection<AppRoles> roles) {
		this.roles = roles;
	}
	
}
