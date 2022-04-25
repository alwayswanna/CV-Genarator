package a.gleb.cv.service;

import a.gleb.cv.mapper.CustomModelMapper;
import a.gleb.cv.model.exception.BadRequestException;
import a.gleb.cv.model.exception.IllegalPageCoordinate;
import a.gleb.cv.model.request.RequestModel;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

@Service
public record PdfService(ResponseGenService responseGenService,
                         ImagePDFService imagePDFService,
                         PersonalDataPDFService personalDataPDFService,
                         CustomModelMapper customModelMapper) {

    @SneakyThrows
    public ResponseEntity<Resource> generateCv(RequestModel model, Part inputStreamFile) {
        var template = new File(Objects.requireNonNull(
                getClass().getClassLoader().getResource("templates/template-cv.pdf")).getFile());
        PDDocument document = null;
        var out = new ByteArrayOutputStream();
        try {
            document = PDDocument.load(template);
            // add image to cv;
            document = imagePDFService.addImageToCv(document, inputStreamFile);
            // add personal information to cv;
            document = personalDataPDFService.addPersonalInfoToCV(document, customModelMapper.map(model, model.getContactInfoModel()));
            // add personal skills to cv;
            document = personalDataPDFService.addPersonalSkills(document, model.getProfessionalSkills());
            // possible page can be ended; TODO: make every where check on Y coordinate;
            var pairAfterEducation = personalDataPDFService.addEducationInformation(document, model.getEducationInfoModelList());
            if (pairAfterEducation.getLeft() < 20) {
                var documentForWork = pairAfterEducation.getRight();
                documentForWork.addPage(new PDPage());
                personalDataPDFService.addWorkInformation(document, model.getWorkExperienceList(), pairAfterEducation.getLeft());
            }
            // add work history to cv;
            document = personalDataPDFService.addWorkInformation(document, model.getWorkExperienceList(),
                    pairAfterEducation.getLeft()).getRight();
            document.save(out);
            document.close();
        } catch (IllegalPageCoordinate e) {
            throw new IllegalPageCoordinate(e.getMessage());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return responseGenService.createResponseFile(new ByteArrayResource(out.toByteArray()), MediaType.MULTIPART_FORM_DATA_VALUE);
    }
}
