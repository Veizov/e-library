package bg.tu.sofia.validator;


import bg.tu.sofia.model.User;
import bg.tu.sofia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final int DESCRIPTION_LIMIT = 300;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    //TODO
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String description = user.getDescription();
        String email = user.getEmail();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sname", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.field.valid");


        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null)
            errors.rejectValue("email", "error.user.exist");
        else {
            if (!user.getPassword().equalsIgnoreCase(user.getConfirmPassword()))
                errors.rejectValue("confirmPassword", "error.confirm", "Password don't match");
            if (!StringUtils.isEmpty(email) && !email.matches(EMAIL_PATTERN))
                errors.rejectValue("email", "error.email.format");
            if(!StringUtils.isEmpty(description) && description.length() > DESCRIPTION_LIMIT)
                errors.rejectValue("description", "error.description.length", "The maximum number of allowed characters is 300");

        }


    }
}
