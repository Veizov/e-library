package bg.tu.sofia.utils;

import bg.tu.sofia.model.Blobs;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class FileUtils {

    public static void downloadFile(HttpServletResponse response, Blobs blob) throws IOException {
        if(null != blob){
            try {
                String encodedFileName = URLEncoder.encode(blob.getFilename(), "UTF-8").replaceAll("\\+", " ");
                response.setCharacterEncoding("UTF-8");
                response.setContentType(blob.getContentType());
                response.setHeader("Content-disposition", "attachment; filename=" + encodedFileName);
                response.getOutputStream().write(blob.getContent());
                response.flushBuffer();
            } catch (IOException e) {
                throw new IOException("Error while download file !");
            }
        }
    }
}
