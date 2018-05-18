package bg.tu.sofia.utils;

import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.BookCategory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static Date endOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 990);
        return c.getTime();
    }

    public static Book convertFileToBook(File file, String bookTitle, BookCategory category) throws IOException {
        Book book = new Book();
        Blobs blobs = FileUtils.convertPdfFile(file);
        book.setFile(blobs);
        book.setTitle(bookTitle);
        book.setCategory(category);
        book.setCreatedDate(new Date());
        return book;
    }


}
