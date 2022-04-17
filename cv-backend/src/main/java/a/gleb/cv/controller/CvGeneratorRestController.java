package a.gleb.cv.controller;

import a.gleb.cv.model.request.RequestModel;
import a.gleb.cv.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("cv-controller")
@RequestMapping("/api/v1")
@Controller
@Tag(name = "cv-generator-controller")
public record CvGeneratorRestController(PdfService pdfService) {

    private static final String CV_CONTROLLER = "cv-generator-controller";

    @Operation(
            summary = "Create new CV",
            tags = {CV_CONTROLLER}
    )
    @PostMapping(value = "/cv-gen", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Resource> generate(
            @RequestPart("file")
                    MultipartFile file,
            @RequestPart("model") RequestModel model
    ) {
        return pdfService.generateCv(model, file);
    }
}
