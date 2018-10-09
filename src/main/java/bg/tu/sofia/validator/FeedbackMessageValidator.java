package bg.tu.sofia.validator;


import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.FeedbackMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Objects;

@Component
public class FeedbackMessageValidator implements Validator {

    private static final int MAX_TEXT_LENGTH = 2000;

    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        FeedbackMessage feedbackMessage = (FeedbackMessage) target;
        String text = feedbackMessage.getText();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "empty.field.valid");

        if (!StringUtils.isEmpty(text) && text.length() > MAX_TEXT_LENGTH)
            errors.rejectValue("text", "error.text.length");

    }
}
