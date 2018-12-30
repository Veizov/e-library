package bg.tu.sofia.utils;

import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.BookCategory;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

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
        Blobs coverOfPdf = getCoverOfPdf(file);
        if (Objects.nonNull(coverOfPdf))
            book.setCover(coverOfPdf);
        return book;
    }

    private static PDDocument covertToPdDocument(byte[] bytes) {
        PDDocument doc;
        try {
            doc = PDDocument.load(bytes);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return doc;
    }

    public static Integer getNumberOfPages(byte[] bytes) {
        PDDocument pdDocument = covertToPdDocument(bytes);
        if (null == pdDocument)
            return null;
        int numberOfPages = pdDocument.getNumberOfPages();
        try {
            pdDocument.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return numberOfPages;
    }

    public static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static String getRequestURL(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

    private static Blobs getCoverOfPdf(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            byte[] bytes = Files.readAllBytes(path);
            PDDocument pdDocument = Utils.covertToPdDocument(bytes);
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
            if(Objects.isNull(bim))
                return null;
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bim, "png", out);

            Blobs blobs = new Blobs();
            blobs.setFilename(file.getName() + "-Cover");
            blobs.setContentType("image/png");
            blobs.setContent(out.toByteArray());
            blobs.setCreatedDate(new Date());
            blobs.setFilesize(out.size());

            out.close();
            return blobs;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }
}
