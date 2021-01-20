/**
 * 
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.shiro.security.ShiroSecurityPolicy;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.NibssTSARevenueCollectionSecurityPolicyApi; 

/**
 * @author David
 * 
 */
public class NibssTSARevenueCollectionSecurityPolicyApiImpl extends
		ShiroSecurityPolicy implements
		NibssTSARevenueCollectionSecurityPolicyApi {

	public NibssTSARevenueCollectionSecurityPolicyApiImpl(String iniFilePath,byte[] passphrase) {
		super(iniFilePath, passphrase, true);   // true indicates non-persisted
												// authentication, i.e., always
												// re-authentication per use.
		List<Permission> permissionList = new ArrayList<Permission>();
		permissionList.add(new WildcardPermission("redbox:request-manager-access:*", false));
		setPermissionsList(permissionList);
	}
}
