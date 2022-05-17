package studentmanagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.dao.ClassDAO;
import studentmanagement.dao.StudentDAO;
import studentmanagement.dto.ClassResponseDTO;
import studentmanagement.dto.StudentRequestDTO;
import studentmanagement.dto.StudentResponseDTO;
import studentmanagement.model.StudentBean;

@Controller
public class StudentController {
	@Autowired
	private StudentDAO sdao;
	
	
	@RequestMapping(value="/setupaddstudents", method=RequestMethod.GET)
	public ModelAndView setupaddstudent(HttpSession session,ModelMap model) {
			ClassDAO cdao=new ClassDAO();
			ArrayList<ClassResponseDTO> list = cdao.selectAll();
			session.setAttribute("classlist", list);
		return new ModelAndView("addStudent","sbean",new StudentBean());
	}
	
	@RequestMapping(value="/addstudent", method=RequestMethod.POST)
	public String addstudent(@ModelAttribute("sbean")StudentBean sbean,ModelMap model) {
		if(sdao.checkId(sbean.getStudent_id())) {
			model.addAttribute("iderror", "Id already exists.");
			return "addStudent";
		}
	else if(sbean.getStudent_id().equals("") || sbean.getStudent_name().equals("") || sbean.getClass_name().equals("") || sbean.getStatus().equals("") || sbean.getDay().equals("") || sbean.getMonth().equals("") || sbean.getYear().equals("")) {
			model.addAttribute("fields", "You must fullfill the fields!!");
			return "addStudent";
		}else {	
		int intYear = Integer.parseInt(sbean.getYear());
		int intMonth = Integer.parseInt(sbean.getMonth());
		int intDay = Integer.parseInt(sbean.getDay());
		LocalDate date = LocalDate.of(intYear, intMonth, intDay);
		sbean.setDate(date);
			StudentRequestDTO dto=new StudentRequestDTO();
			dto.setStudent_id(sbean.getStudent_id());
			dto.setStudent_name(sbean.getStudent_name());
			dto.setClass_name(sbean.getClass_name());
			dto.setDate(sbean.getDate());
			dto.setStatus(sbean.getStatus());
			int rs=sdao.insertData(dto);
			if(rs>0) {
				model.addAttribute("success", "Successfully Registered!!");
				return "addStudent";
			}else {
				model.addAttribute("error", "Insert Failed!!");
				return "addStudent";		
			}		
		}
	}
	
	@RequestMapping(value="/setupsearchstudent", method=RequestMethod.GET)
	public String setupsearchstudent() {
		return "searchStudentForm";
	}
	
	@RequestMapping(value="/searchstudent", method=RequestMethod.POST)
	public String searchstudent(@RequestParam("studentId") String studentId,@RequestParam("studentName") String studentName,
			@RequestParam("className") String className,ModelMap model) {
		
		ArrayList<StudentResponseDTO> list=null;
		if(studentName.equals("") && className.equals("")) {
			list = sdao.searchById(studentId);
		}else if(studentId.equals("") && className.equals("")){
			list = sdao.searchByName(studentName);
		}else if(studentId.equals("") && studentName.equals("")){
			list = sdao.searchByClass(className);
		}else {
			list = sdao.searchById(studentId);
			if (list.size() == 0) {
				list = sdao.searchByName(studentName);
			}
		}
		model.addAttribute("studentList", list);
		return ("searchStudentResult");
	}
	
	@RequestMapping(value="/setupstudentupdate/{student_id}", method=RequestMethod.GET)
	public ModelAndView setupstudentupdate(@PathVariable String student_id, ModelMap model, HttpSession session) {
		StudentResponseDTO dto = new StudentResponseDTO();
		dto = sdao.selectById(student_id);
		String date = null;
		date = dto.getDate();
		dto.setYear(date.substring(0, 4));
		dto.setMonth(date.substring(5, 7));
		dto.setDay(date.substring(8));
		
		ClassDAO cdao=new ClassDAO();
		ArrayList<ClassResponseDTO> list = cdao.selectAll();
		session.setAttribute("classlist", list);
		model.addAttribute("editStuInfo", dto);
		return new ModelAndView("updateStudent","sbean", sdao.selectById(student_id));
	}
	
	@RequestMapping(value="/updatestudent", method=RequestMethod.POST)
	public String updatestudent(@ModelAttribute("sbean") @Validated StudentBean sbean, BindingResult bs, ModelMap model) {
		if(bs.hasErrors()) {
			return "updateStudent";
		}		
			int intYear = Integer.parseInt(sbean.getYear());
			int intMonth = Integer.parseInt(sbean.getMonth());
			int intDay = Integer.parseInt(sbean.getDay());
			LocalDate date = LocalDate.of(intYear, intMonth, intDay);
			sbean.setDate(date);
				StudentRequestDTO dto=new StudentRequestDTO();
				dto.setStudent_id(sbean.getStudent_id());
				dto.setStudent_name(sbean.getStudent_name());
				dto.setClass_name(sbean.getClass_name());
				dto.setDate(sbean.getDate());
				dto.setStatus(sbean.getStatus());
				int rs=sdao.updateData(dto);
				if(rs>0) {
					model.addAttribute("success", "Successfully Updated!!");
					return "updateStudent";
				}else {
					model.addAttribute("error", "Update Failed!!");
					return "updateStudent";		
			}
		}
	
	@RequestMapping(value="/deletestudent/{student_id}", method=RequestMethod.GET)
	public String deleteuser(@PathVariable String student_id,ModelMap model) {
		StudentRequestDTO dto=new StudentRequestDTO();
		dto.setStudent_id(student_id);
		int res=sdao.deleteData(dto);
		if(res>0) {
			
			return "searchStudentForm";
		}else{
			model.addAttribute("deletefail", "Delete Failed!!");
			return "searchStudentForm";
		}
		
	}
	
	
	
}
