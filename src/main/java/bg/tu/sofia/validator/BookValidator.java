package bg.tu.sofia.validator;


import bg.tu.sofia.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private static final int DESCRIPTION_LIMIT = 2000;

    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    //TODO
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        String description = book.getDescription();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cover.filename", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file.filename", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category.id", "empty.field.valid");

        if (!StringUtils.isEmpty(description) && description.length() > DESCRIPTION_LIMIT)
            errors.rejectValue("description", "error.description.length", "The maximum number of allowed characters is 2000");

    }
}
