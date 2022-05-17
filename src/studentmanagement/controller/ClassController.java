package studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.dao.ClassDAO;
import studentmanagement.dto.ClassRequestDTO;
import studentmanagement.model.ClassBean;
@Controller
public class ClassController {
	
	@Autowired
	private ClassDAO cdao;
	
	@RequestMapping(value="/setupaddclass", method=RequestMethod.GET)
	public ModelAndView setupaddclass() {
		return new ModelAndView("addClass","cbean",new ClassBean());
	}
	
	@RequestMapping(value="/addclass", method=RequestMethod.POST)
	public String addclass(@ModelAttribute("cbean")ClassBean cbean,ModelMap model) {
//		if(bs.hasErrors()) {
//			return "addClass";
//		}
		if(cdao.checkId(cbean.getClass_id())) {
			model.addAttribute("iderror","Id already exists!!");
			return "addClass";
		}
	else if(cbean.getClass_id().equals("") || cbean.getClass_name().equals("")) {
			model.addAttribute("fields", "You must fullfill the fields!!");
			return "addClass";	
		}else {
			ClassRequestDTO dto=new ClassRequestDTO();
			dto.setClass_id(cbean.getClass_id());
			dto.setClass_name(cbean.getClass_name());
			int rs=cdao.insertData(dto);
			if(rs>0) {
				model.addAttribute("success", "Successfully Registered!!");
				return "addClass";
			}else {
				model.addAttribute("fail", "Insert Failed!!");
				return "addClass";		
		}
		}
					
	}
}
