package a.gleb.cv.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ACCEPT_CHARSET;

@Service
public class ResponseGenService {
    private static final String CONTENT_PREFIX = "attachment; filename=";

    public ResponseEntity<Resource> createResponseFile(Resource body, String contentType) {
        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, CONTENT_PREFIX + "\"" + "done_cv" + "\"")
                .header(CONTENT_TYPE, contentType)
                .header(ACCEPT_CHARSET, "UTF-8")
                .body(body);
    }

}
