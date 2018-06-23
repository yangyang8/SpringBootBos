package com.hailong.bos.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.hailong.bos.dao.admin.TPermissionDao;
import com.hailong.bos.dao.user.UserDao;
import com.hailong.bos.domain.TPermissions;
import com.hailong.bos.domain.User;

/**
 * 实现这二个认证和授权的方法
 * @author Administrator
 *
 */

public class LoginRealm extends AuthorizingRealm {

	//使用Spring 框架注入我们的UserDao对象
	@Autowired
	private UserDao userDao;
	
	//注入权限表的相关Dao的操作
	@Autowired
	private TPermissionDao permissionDao;
	
	//认证的过程
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("我们的Shiro的认证方法执行了");
		UsernamePasswordToken to=(UsernamePasswordToken) token;
		//String username=token.getPrincipal();
		//进行调用这个认证的方法，现在进行查询数据库，我们的Shiro框架的做法是要根据用户名查询出用户的密码，然后根据密码传给Shiro框架，Shiro框架帮我们检查密码是否相等操作
		User user=userDao.findByUsername(to.getUsername());
		if(user==null){
			return null;//返回给securityManager
		}
		//创建一个认证信息
		/**
		 * 第一个参数我们在认证当中可以拿到，这样我们就可以在UserController当中取出来放入到我们的Session当中
		 */
		AuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		
		return info; //返回null,则Shiro框架会抛出一个没有这样的账号异常
	}
	
	//用户的授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//因为我们的配置方法shiro的配置当中已经设置了他的权限过滤器的相关的权限操作，所以我们要进行相关的权限的授权操作
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		//为当前的用户授于下面的这个字符串的权限
		//info.addStringPermission("staff-list");
		//得到当前登录的用户
		User user=(User) principals.getPrimaryPrincipal();
		List<TPermissions> list=null;
		//查询数据的相关的权限信息,然后进行遍历加入这个权限认证当中,如果当前的用户名为admin，则不用查询数据，直接给所有的权限
		if(user!=null&&"admin".equalsIgnoreCase(user.getUsername())){
			//则直接查询权限表，给当前用户设置所有的权限
			list=permissionDao.findAll();
		}else{
			list=permissionDao.findByTpermissoinAndUserId(user.getId());
		}
		
		//进行权限的相关的授权操作
		for(TPermissions tp:list){
			info.addStringPermission(tp.getCode()); //使用code来表示授权代码的相关的操作
		}
		return info;
	}

}
