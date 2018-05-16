package bg.tu.sofia.utils;

import bg.tu.sofia.model.Blobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileUtils {
    private static Logger LOG = LoggerFactory.getLogger(FileUtils.class);
    private static final String PDF_FILE_EXTENSION = ".pdf";
    private static final String PDF_FILE_CONTENT_TYPE = "application/pdf";

    public static void downloadFile(HttpServletResponse response, Blobs blob) throws IOException {
        if (null != blob) {
            try {
                String encodedFileName = URLEncoder.encode(blob.getFilename(), "UTF-8").replaceAll("\\+", " ");
                response.setCharacterEncoding("UTF-8");
                response.setContentType(blob.getContentType());
                response.setHeader("Content-disposition", "attachment; filename=" + encodedFileName);
                response.getOutputStream().write(blob.getContent());
                response.flushBuffer();
            } catch (IOException e) {
                LOG.error(e.getMessage(),e);
                throw new IOException("Error while download file !");
            }
        }
    }

    public static List<File> getPdfFiles(File folder) {
        List<File> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory())
                files.addAll(getPdfFiles(file));
            else if (file.getName().toLowerCase().endsWith(PDF_FILE_EXTENSION))
                files.add(file);
        }
        return files;
    }

    public static Blobs convertPdfFile(File file) throws IOException {
        Blobs blob = new Blobs();
        blob.setFilename(file.getName());
        blob.setCreatedDate(new Date());
        blob.setContentType(PDF_FILE_CONTENT_TYPE);
        Path path = Paths.get(file.getAbsolutePath());
        byte[] bytes = Files.readAllBytes(path);
        blob.setContent(bytes);
        blob.setFilesize(Math.toIntExact(file.length()));
        return blob;
    }

    public static long folderSize(File directory) throws IOException {
        Path path = Paths.get(directory.getAbsolutePath());
        return Files.walk(path)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
    }

}
