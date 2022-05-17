package studentmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import studentmanagement.dao.UserDAO;
import studentmanagement.dto.UserRequestDTO;
import studentmanagement.dto.UserResponseDTO;
import studentmanagement.model.UserBean;

@Controller
public class UserController {
	@Autowired
	private UserDAO dao;
//	@Qualifier("userValidator")
//	   private Validator validator;
//
//	   @InitBinder
//	   private void initBinder(WebDataBinder binder) {
//	      binder.setValidator(validator);
//	   }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login() { 
		return "Login";
	}
	
	@RequestMapping(value = "/finalexam", method = RequestMethod.POST)
	public String finalexampage(@RequestParam("userid") String userid,@RequestParam("userpassword") String userpassword,
	HttpSession session,ModelMap model) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = formatter.format(date);
		UserDAO dao = new UserDAO();
		if(dao.check(userid, userpassword)) {
			UserResponseDTO dto=dao.selectOneById(userid);
			session.setAttribute("userInfo", dto);
			session.setAttribute("date", currentDate);
			return "Final_exam";
		}else {
			model.addAttribute("loginfail","Login Failed");
			return "Login";
		}
	}
	
//	if(session.getAttribute("userInfo") == null){
//		response.sendRedirect("/StudentManagementSpring/logout");
		
	@RequestMapping(value = "/finalexampage", method = RequestMethod.GET)
	public String finalexampage() {
		return "Final_exam";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "redirect:/";
	}

	
	@RequestMapping(value="/setupusersearch", method=RequestMethod.GET)
	public String usersearchpage() { 
		return "UserSearch";
	}
	
	@RequestMapping(value="/usersearch", method=RequestMethod.POST)
	public String usersearch(@RequestParam("uid") String uid,@RequestParam("uname") String uname,ModelMap model) {
		UserDAO dao=new UserDAO();
		UserResponseDTO searchInfo=null;
		if(uid.equals("")) {
			searchInfo=dao.selectOneByname(uname);
		}else if(uname.equals("")){
			searchInfo=dao.selectOneByid(uid);
		}else {
			searchInfo = dao.selectOneByname(uname);
		}
		
		if (searchInfo.getUser_id() == null) {
			searchInfo.setUser_id("not found");
			searchInfo.setUser_name("not found");
			model.addAttribute("list", searchInfo);
			return "displaySearchInfo";
		} else {
			model.addAttribute("list", searchInfo);
			return "displaySearchInfo";
		}		
	}
	
	@RequestMapping(value="/setupadduser", method=RequestMethod.GET)
	public ModelAndView setupadduser() {
		return new ModelAndView("addUser","bean",new UserBean());
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public String adduser(@ModelAttribute("bean")UserBean bean,ModelMap model) {
//		if(bs.hasErrors()) {
//			return "addUser";
//		}
		if(dao.checkId(bean.getUser_id())) {
			model.addAttribute("iderror", "Id already exists.");
			return "addUser";
		}else if(!bean.getUser_password().equals(bean.getCon_password())) {
			model.addAttribute("pwerror", "Passwords do not match!!");
			return "addUser";
		}
	else if(bean.getUser_id().equals("") || bean.getUser_name().equals("") || bean.getUser_password().equals("") || bean.getCon_password().equals("")) {
	model.addAttribute("fields", "You must fullfill the fields.");
	return "addUser";
		}else {
			UserRequestDTO dto=new UserRequestDTO();
			dto.setUser_id(bean.getUser_id());
			dto.setUser_name(bean.getUser_name());
			dto.setUser_password(bean.getUser_password());
			int rs=dao.insertData(dto);
			if(rs>0) {
				model.addAttribute("success", "Successfully Registered!!");
				return "addUser";
			}else {
				model.addAttribute("error", "Insert Failed!!");
				return "addUser";		
		}
		}
					
	}
	
	@RequestMapping(value="/setupUpdateUser/{user_id}", method=RequestMethod.GET)
	public ModelAndView setupUpdatebook(@PathVariable String user_id) {
		UserRequestDTO dto=new UserRequestDTO();
		dto.setUser_id(user_id);
		return new ModelAndView("updateUser","bean", dao.selectOne(dto));
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.POST)
	public String updatebook(@ModelAttribute("bean") @Validated UserRequestDTO bean, BindingResult bs, ModelMap model) {
		if(bs.hasErrors()) {
			return "updateUser";
		}
		int rs=dao.updateData(bean);
		if(rs==0) {
			model.addAttribute("error", "Update Failed");
			return "updateUser";
		}else {
			model.addAttribute("success","Successfully Updated!!");
			return "updateUser";
		}
	}
	
	@RequestMapping(value="/deleteuser/{user_id}", method=RequestMethod.GET)
	public String deleteuser(@PathVariable String user_id,ModelMap model) {
		UserRequestDTO dto=new UserRequestDTO();
		dto.setUser_id(user_id);
		int res=dao.deleteData(dto);
		if(res==0) {
			model.addAttribute("error", "Delete Failed");
		}
		return "redirect:/setupusersearch";
	}
	
}