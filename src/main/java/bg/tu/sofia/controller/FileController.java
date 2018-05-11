package bg.tu.sofia.controller;

import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.service.BlobsService;
import bg.tu.sofia.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private BlobsService blobsService;

    @RequestMapping(value = "/download-file/{blob}")
    @ResponseBody
    public void downloadFile(@PathVariable("blob") Integer blob, HttpServletResponse response) throws IOException {
        Blobs file = blobsService.findBlobsById(blob);
        if (null != file) {
            try {
                FileUtils.downloadFile(response, file);
            } catch (IOException e) {
                throw new IOException("Cannot download file !");
            }
        }
    }

    @RequestMapping(value = "/cover-image/{id}")
    @ResponseBody
    public void getCoverImage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Blobs blob = blobsService.findBlobsById(id);
        if (null != blob) {
            try {
                response.setContentType(blob.getContentType());
                response.getOutputStream().write(blob.getContent());
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                throw new IOException("Error while show cover file !");
            }
        }
    }

}
