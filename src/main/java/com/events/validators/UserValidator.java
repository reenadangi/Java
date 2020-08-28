package com.events.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.events.models.User;
import com.events.repositories.UserRepository;



@Component
public class UserValidator implements Validator{
	@Autowired
	private UserRepository uRepo;
	 @Override
	    public boolean supports(Class<?> clazz) {
	        return User.class.equals(clazz);
	    }
	 @Override
	 public void validate(Object target, Errors errors) {
	        User user = (User) target;
	        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
	            errors.rejectValue("passwordConfirmation", "Match","password should match");
	        }         
	        
	       if(uRepo.findByEmail(user.getEmail())!=null) {
	    	   errors.rejectValue("email","unique", "Email already exist");
	       }
	    }
}
