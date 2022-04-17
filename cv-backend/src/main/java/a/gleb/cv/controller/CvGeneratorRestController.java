package a.gleb.cv.controller;

import a.gleb.cv.model.request.RequestModel;
import a.gleb.cv.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController("cv-controller")
@RequestMapping("/api/v1")
@Controller
@Tag(name = "cv-generator-controller")
@Slf4j
public record CvGeneratorRestController(PdfService pdfService, ObjectMapper objectMapper) {

    private static final String CV_CONTROLLER = "cv-generator-controller";

    @Operation(
            summary = "Create new CV",
            tags = {CV_CONTROLLER}
    )
    @PostMapping(value = "/cv-gen", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Resource> generate(final HttpServletRequest request) throws ServletException, IOException {
        return pdfService.generateCv(
                objectMapper.readValue(request.getParameter("model"), RequestModel.class),
                request.getPart("file"));
    }
}
