package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_permissions")
public class MobilePermissions implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mobile_permissions_id;
	private String permission_name;
	private int status;
	
	private String packageName;
	
	private String module;

	public int getMobile_permissions_id() {
		return mobile_permissions_id;
	}

	public void setMobile_permissions_id(int mobile_permissions_id) {
		this.mobile_permissions_id = mobile_permissions_id;
	}

	public String getPermission_name() {
		return permission_name;
	}

	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    MobilePermissions that = (MobilePermissions) o;

	    return Objects.equals(packageName, that.packageName) &&
	           Objects.equals(module, that.module) &&
	           Objects.equals(permission_name, that.permission_name);
	}


	    @Override
	    public int hashCode() {
	        return Objects.hash(module, packageName, permission_name);
	    }

}
