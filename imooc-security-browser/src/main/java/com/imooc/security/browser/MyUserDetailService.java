package com.imooc.security.browser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UserDetailsService是SpringSecurity的一个接口，
 * 只有一个方法：根据用户名获取用户详情
 * ClassName: MyUserDetailService 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月28日
 */
@Component
public class MyUserDetailService implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * UserDetails接口，实际可以自己实现这个接口，返回自己的实现类
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名："+username);
		//根据用户名查询用户信息
		
		//User：springsecurity 对 UserDetails的一个实现
		//为了演示在这里用passwordEncoder加密一下密码，实际中在注册时就加密，此处直接拿出密码
		String password = passwordEncoder.encode("123456");
		System.err.println("加密后密码：  "+password);
		//参数：用户名|密码|是否启用|账户是否过期|密码是否过期|账户是否锁定|权限集合
		return new User(username,password,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
