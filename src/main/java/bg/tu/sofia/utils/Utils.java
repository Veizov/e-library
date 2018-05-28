package bg.tu.sofia.utils;

import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.BookCategory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    private static Logger LOG = LoggerFactory.getLogger(Utils.class);

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
        Date currentDate = new Date();
        Blobs blobs = FileUtils.convertPdfFile(file);
        book.setFile(blobs);
        book.setTitle(bookTitle);
        book.setCategory(category);
        book.setNumberOfPages(getNumberOfPages(blobs.getContent()));
        book.setCreatedDate(currentDate);
        book.setLastUpdate(currentDate);
        return book;
    }

    private static PDDocument covertToPdDocument(byte[] bytes){
        PDDocument doc;
        try {
            doc = PDDocument.load(bytes);
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        }
        return doc;
    }

    public static Integer getNumberOfPages(byte[] bytes){
        PDDocument pdDocument = covertToPdDocument(bytes);
        if(null == pdDocument)
            return null;
        int numberOfPages = pdDocument.getNumberOfPages();
        try {
            pdDocument.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }
        return numberOfPages;
    }

}
