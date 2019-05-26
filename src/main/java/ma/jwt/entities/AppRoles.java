package ma.jwt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppRoles {
	@Id @GeneratedValue
	private Long id;
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public AppRoles() {
		super();
	}

	public AppRoles(String roleName) {
		super();
		this.roleName = roleName;
	}
	
}
