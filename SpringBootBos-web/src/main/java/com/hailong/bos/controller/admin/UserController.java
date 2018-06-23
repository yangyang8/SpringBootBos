package com.hailong.bos.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.hailong.bos.config.StringToDateConfig2;
import com.hailong.bos.domain.TRole;
import com.hailong.bos.domain.User;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.UserService;
import com.hailong.bos.utils.MD5Utils;
import com.hailong.bos.utils.SessionUtils;

@RestController
public class UserController extends StringToDateConfig2 {
	// TODO 这里的数据转换有点问题
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello(){
		
		return "Hello Bos";
	}
	
	@RequestMapping("/")
	public ModelAndView root(HttpServletRequest request){
		//StringBuffer buffer=request.getRequestURL();
		//System.out.println(buffer.toString());
		return new ModelAndView("index");
	}
	
	
/*	@RequestMapping("/page_common_index.action")
	public ModelAndView index(){
		return new ModelAndView("common/index");
	}
	
	
	@RequestMapping("/page_common_home.action")
	public ModelAndView home(){
		return new ModelAndView("common/home");
	}*/
	
	//进行操作下面的链接操作

	//进行一次性处理完所有的请求的映射关系

	//进行一次性处理完所有的请求的映射关系
	@RequestMapping("/page_*_*.action")
	public ModelAndView staff(HttpServletRequest request){
		StringBuffer buffer=request.getRequestURL();
		//http://localhost:8088/page_common_index.action
		int last=buffer.toString().lastIndexOf(".");
		String path=buffer.toString().substring(0,last);
		String paths[]=path.split("_");
		//System.out.println(buffer.toString());
		return new ModelAndView(paths[1]+"/"+paths[2]);
	}
	
	//跳转登录页面的方法
	@RequestMapping("/loginIn.action")
	public ModelAndView loginIn(){
		return new ModelAndView("login");
	}
	
	//进行我们的登录的页面的操作方法
	/**
	 * 使用Shiro的权限认证的方式进行认证操作
	 * @param password
	 * @param username
	 * @param checkcode
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(String password,String username,String checkcode,HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		//System.out.println(session.getAttribute("currentUser"));
		String vaildCode=(String)session.getAttribute("key");
		if(!StringUtils.isEmpty(checkcode)&&checkcode.equals(vaildCode)){
			 //使用Shiro的方式进行认证操作
			//通过shiro的流程方式进行操作
			//1 先得到subject 可以理解成登录的对象
			Subject subject=SecurityUtils.getSubject();//得到Subject对象
			password=MD5Utils.md5(password);
			//创建一个权限认证的Token
			AuthenticationToken token=new UsernamePasswordToken(username, password);
			//然后调用我们的SecurityManager对象
			
			try{
				//返回值为空，那如果来判断这个方法操作成功否呢，其实这个如果是用户名，或密码不正确时会抛出异常的，
				//所以我们就通过有没有异常发生来判断登录成功否
				subject.login(token); //这个方法会调用我们的SecurityManager接口当中的方法，然后SecurityManager接口会调用Relam当中的方法
			}catch(Exception e){
				e.printStackTrace();
				return new ModelAndView("login","",model);
			}
			//得到当前登录的数据
			User user=(User) subject.getPrincipal();//也就是我们的LoginRealm当中传过来的那个数据
			//放入Session当中
			HttpSession sess=SessionUtils.getSession(request);
			if(sess==null){
				//session无效，在这里进行页面跳转，返回到登录页面
				return new ModelAndView("login");
			}
			sess.setAttribute("currentUser",user);
			return new ModelAndView("index");
			//通过SecurityManager来调用rleam的对象的方法
		}else{
			//设置提示信息，同时返回到登录页面
			model.addAttribute("error","验证码不正确");
			return new ModelAndView("login","",model);
		}
	}
	
	
	//进行我们的登录的页面的操作方法
	@RequestMapping("/login_back")
	public ModelAndView login_back(String password,String username,String checkcode,HttpServletRequest request,Model model){
			HttpSession session=request.getSession();
			String vaildCode=(String)session.getAttribute("key");
			if(!StringUtils.isEmpty(checkcode)&&checkcode.equals(vaildCode)){
				//说明验证码正确
				//接下来开始进行验证用户名和密码
				User user=userService.login(username,password);
				if(user!=null){
					//把当前用户放入到我们的Session当中
					session.setAttribute("currentUser",user);
					//进行跳转到首页当中
					return new ModelAndView("index");
				}else{
					//说明是用户名或密码错误
					//设置提示信息，同时返回到登录页面
					model.addAttribute("error","用户名或密码不正确");
					return new ModelAndView("login","",model);
				}
			}else{
				//设置提示信息，同时返回到登录页面
				model.addAttribute("error","验证码不正确");
				return new ModelAndView("login","",model);
			}
		}
	
	
	
	//注销登录的操作方法
	@RequestMapping("/loginOut.action")
	public ModelAndView loginOut(HttpServletRequest request,HttpServletResponse response){
		//在跳转到登录页面之前，要进行清空Session当中的用户数据
		HttpSession session=request.getSession();
		
		//session.removeAttribute("currentUser");
		Subject subject=SecurityUtils.getSubject();
		subject.logout();//注销
		//session.invalidate();//清空Session当中用户的数据
		/*try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//进行重定向操作
		return new ModelAndView("redirect:/login");
	}
	
	//修改密码页面操作
	@RequestMapping("/editPassword")
	public void editPassword(String password,HttpServletRequest request,HttpServletResponse response){
		String f="1";
		//获取登录用户
		User user=SessionUtils.getLoginUser(request);
		try{
			//不用修改Session当中的用户的密码，因为他会从数据库当中查询出来
			//进行根据用户Id来更新数据
			userService.updatePasswordById(user.getId(),password);
		}catch(Exception e){
			f="0";
			e.printStackTrace();
		}
		
		//return f;
		try {
			//返回成功是否的标志位，返回给ajax的数据，返回给ajax的数据不能是直接返回给他
			response.getWriter().print(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//增加用户
	@RequestMapping("/addUser.action")
	public ModelAndView addUser(User user,String roleId[]){
		userService.saveOrUpdate(user,roleId);
		return new ModelAndView("admin/userlist");
	}
	
	//进行查询所有的角色的数据
	@RequestMapping("/getAllRoles.action")
	public String getAllRoles(){
		List<TRole> roleList=userService.getAllRoles();
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(TRole.class,new String[]{"id","name"});
		String json=JSONArray.toJSONString(roleList,filter);
		return json;
	}
	
	//查询所有的用户数据
	@RequestMapping("/pageUserList.action")
	public String pageUserList(Integer page,Integer rows){
		JsonUtils<User> user=userService.pageUserList(page,rows);
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(User.class,
				new String[]{"id","username","roleName","salary","birthday","gender","station","telephone","remark"});
		String js=JSONArray.toJSONString(user,filter);
		return js;
	}
	

}
