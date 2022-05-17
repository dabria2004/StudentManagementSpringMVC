package studentmanagement.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import studentmanagement.model.UserBean;

public class UserValidator implements Validator {
	 @Override
	   public boolean supports(Class<?> clazz) {
	      return UserBean.class.isAssignableFrom(clazz);
	   }

	 @Override
	   public void validate(Object target, Errors errors) {		
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	         "user_name", "required.user_name","User name is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	 	         "user_id", "required.user_id","User id is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	 	         "user_password", "required.user_password","User password is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	 	         "con_password", "required.con_password","Confirm Password is required.");
	   }
}
