package ma.jwt.service;

import ma.jwt.entities.AppRoles;
import ma.jwt.entities.AppUSer;

public interface AccountService {
	public AppUSer saveUser(AppUSer u);
	public AppRoles saveRole(AppRoles r);
	public void addRoleToUser(String u,String r);
	public AppUSer findUserByUSername(String u);
}
