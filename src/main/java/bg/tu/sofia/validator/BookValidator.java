package bg.tu.sofia.validator;


import bg.tu.sofia.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Objects;

@Component
public class BookValidator implements Validator {

    private static final int MIN_YEAR = 1960;
    private static final int DESCRIPTION_LIMIT = 2000;
    private static final int ISBN_LENGTH_FROM_ISO_OLD = 10;
    private static final int ISBN_LENGTH_FROM_ISO_2007 = 13;
    public static final String NUMBER_PATTERN = "^[0-9]+$";

    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    //TODO
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        String description = book.getDescription();
        String isbn = book.getIsbn();
        Short year = book.getYear();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file.filename", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category.id", "empty.field.valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "language.id", "empty.field.valid");

        if (!StringUtils.isEmpty(description) && description.length() > DESCRIPTION_LIMIT)
            errors.rejectValue("description", "error.description.length");

        if (!StringUtils.isEmpty(isbn)) {
            if (!isbn.matches(NUMBER_PATTERN))
                errors.rejectValue("isbn", "error.isbn.number");
            else if (isbn.length() != ISBN_LENGTH_FROM_ISO_2007 && isbn.length() != ISBN_LENGTH_FROM_ISO_OLD)
                errors.rejectValue("isbn", "error.isbn.length");
        }

        if (Objects.nonNull(year) && (year < MIN_YEAR || year > Calendar.getInstance().get(Calendar.YEAR)))
            errors.rejectValue("year", "error.year.format");

    }
}
