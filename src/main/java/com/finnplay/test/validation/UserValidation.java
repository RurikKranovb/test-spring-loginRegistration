package com.finnplay.test.validation;

import com.finnplay.test.model.User;
import com.finnplay.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UserValidation implements Validator
{

    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private final IUserService userService;

    public UserValidation(IUserService iUserService) {this.userService = iUserService;}

    @Override
    public boolean supports(Class<?> clazz)
    {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Pattern pattern = Pattern.compile(regex);
        User user = (User) target;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().trim().length() < 1 )
        {
            errors.rejectValue("password", "PasswordValid");
        }

//        if (!user.getPasswordConfirm().equals(user.getPassword()))
//        {
//            errors.rejectValue("confirmPassword", "PasswordConfirm");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        if (user.getLastName().trim().length() < 1)
        {
            errors.rejectValue("lastName", "FirstLastName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        if (user.getFirstName().trim().length() < 1)
        {
            errors.rejectValue("firstName", "FirstName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (!pattern.matcher(user.getEmail()).matches())
        {
            errors.rejectValue("email", "MailFormat");
        }

        if (userService.findByEmail(user.getEmail()) != null)
        {
            errors.rejectValue("email", "DuplicateUser");
        }
    }
}
