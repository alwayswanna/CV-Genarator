package a.gleb.cv.service;

import a.gleb.cv.model.exception.IllegalPageCoordinate;
import a.gleb.cv.model.pdf.PersonalInfo;
import a.gleb.cv.model.request.EducationInfoModel;
import a.gleb.cv.model.request.WorkExperienceModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PersonalDataPDFService {

    private static final int X_CONST = 300;
    private static final String LOCATION = "Location";
    private static final String AGE_AND_EXPERIENCE = "Age and experience";
    private static final String CONTACT_INFO = "Contact information";
    private static final String PERSONAL_SKILLS = "Personal skills:";
    private static final String WORK_EXPERIENCE = "Work experience";

    public PDDocument addPersonalInfoToCV(PDDocument document, PersonalInfo personalInfo) throws IOException {
        var page = document.getPage(0);
        var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        // Add name & surname
        addInfo(contentStream, Color.BLACK, 21, X_CONST, 750, String.format("%s %s", personalInfo.getSurname(), personalInfo.getFirstName()));
        // Add job
        addInfo(contentStream, Color.GRAY, 14, X_CONST, 735, personalInfo.getProfession());
        // Add location
        addInfo(contentStream, Color.BLACK, 16, X_CONST, 710, LOCATION);
        addInfo(contentStream, Color.BLACK, 12, X_CONST, 690, String.format("Living place: %s", personalInfo.getLivingPlace()));
        var resRemoteWork = personalInfo.isReadyToRemoteWork() ? "Yes" : "No";
        addInfo(contentStream, Color.BLACK, 12, X_CONST, 675, String.format("Is ready to work remotely: %s", resRemoteWork));
        // Add birthdate and experience:
        addInfo(contentStream, Color.BLACK, 16, X_CONST, 650, AGE_AND_EXPERIENCE);
        var age = Period.between(personalInfo.getBirthDate(), LocalDate.now()).getYears();
        addInfo(contentStream, Color.BLACK, 12, X_CONST, 630, String.format("Age: %s y.o (%s)", age, personalInfo.getBirthDate()));
        addInfo(contentStream, Color.BLACK, 12, X_CONST, 615, String.format("Experience: %s years", personalInfo.getExperience()));
        addInfo(contentStream, Color.BLACK, 12, X_CONST, 600, String.format("Salary: %s USD", personalInfo.getSalary()));
        //Add contact information:
        addInfo(contentStream, Color.BLACK, 16, X_CONST, 580, CONTACT_INFO);
        addContacts(document, contentStream, 560, personalInfo.getPhoneNumber(), new File(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("templates/icons/phone.jpg")).getFile()
        ));
        addContacts(document, contentStream, 545, personalInfo.getEmail(), new File(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("templates/icons/email.png")).getFile()
        ));
        if (StringUtils.isNotBlank(personalInfo.getTelegramLink())) {
            addContacts(document, contentStream, 530, personalInfo.getTelegramLink(), new File(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("templates/icons/telegram.jpg")).getFile()
            ));
        }
        if (StringUtils.isNotBlank(personalInfo.getLinkedInLink())) {
            addContacts(document, contentStream, 515, personalInfo.getLinkedInLink(), new File(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("templates/icons/linked.png")).getFile()
            ));
        }
        if (StringUtils.isNotBlank(personalInfo.getFaceBookLink())) {
            addContacts(document, contentStream, 500, personalInfo.getFaceBookLink(), new File(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("templates/icons/facebook.png")).getFile()
            ));
        }
        if (StringUtils.isNotBlank(personalInfo.getGithubLink())) {
            addContacts(document, contentStream, 485, personalInfo.getGithubLink(), new File(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("templates/icons/github.png")).getFile()
            ));
        }
        if (StringUtils.isNotBlank(personalInfo.getGitlabLink())) {
            addContacts(document, contentStream, 470, personalInfo.getGitlabLink(), new File(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("templates/icons/gitlab.png")).getFile()
            ));
        }
        contentStream.setLineWidth(2);
        contentStream.moveTo(0, 450);
        contentStream.lineTo(page.getMediaBox().getWidth(), 450);
        contentStream.stroke();

        contentStream.close();
        return document;
    }

    public PDDocument addPersonalSkills(PDDocument document, List<String> professionalSkills) throws IOException {
        var page = document.getPage(0);
        var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        addInfo(contentStream, Color.BLACK, 16, 30, 430, PERSONAL_SKILLS);
        var sb = new StringBuffer();
        professionalSkills.forEach(s -> sb.append(String.format(" \u2022 %s", s)));
        if (sb.length() > 91) {
            var sbFirst = new StringBuilder();
            var secondStr = new StringBuilder();
            var firstList = professionalSkills.subList(0, professionalSkills.size() / 2);
            var secondList = professionalSkills.subList(professionalSkills.size() / 2, professionalSkills.size());

            firstList.forEach(s -> sbFirst.append(String.format(" \u2022 %s", s)));
            secondList.forEach(s -> secondStr.append(String.format(" \u2022 %s", s)));

            addInfo(contentStream, Color.BLACK, 12, 30, 410, sbFirst.toString());
            addInfo(contentStream, Color.BLACK, 12, 30, 395, secondStr.toString());

        } else {
            addInfo(contentStream, Color.BLACK, 12, 30, 410, sb.toString());
        }
        contentStream.setLineWidth(2);
        contentStream.moveTo(0, 380);
        contentStream.lineTo(page.getMediaBox().getWidth(), 380);
        contentStream.stroke();
        contentStream.close();
        return document;
    }


    public Pair<Integer, PDDocument> addEducationInformation(PDDocument document, List<EducationInfoModel> educationInfoModelList) throws IOException {
        var page = document.getPage(0);
        var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);

        addInfo(contentStream, Color.BLACK, 16, 30, 360, "Education information:");

        addInfo(contentStream, Color.GRAY, 11, 30, 340, "Start date:");
        addInfo(contentStream, Color.GRAY, 11, 100, 340, "University:");
        addInfo(contentStream, Color.GRAY, 11, 400, 340, "End date:");
        addInfo(contentStream, Color.GRAY, 11, 490, 340, "Level:");

        int paddingLocal = 325;
        for (var i = 0; i < educationInfoModelList.size(); i++) {
            var universityData = educationInfoModelList.get(0);
            addInfo(contentStream, Color.BLACK, 12, 30, paddingLocal, universityData.getStartDate().toString());
            addInfo(contentStream, Color.BLACK, 12, 100, paddingLocal, universityData.getUniversityName());
            addInfo(contentStream, Color.BLACK, 12, 400, paddingLocal, universityData.getEndDate().toString());
            addInfo(contentStream, Color.BLACK, 12, 490, paddingLocal, universityData.getLevel().description);
            paddingLocal -= 15;
        }

        paddingLocal -= 15;
        contentStream.setLineWidth(2);
        contentStream.moveTo(0, paddingLocal);
        contentStream.lineTo(page.getMediaBox().getWidth(), paddingLocal);
        contentStream.stroke();
        contentStream.close();

        return Pair.of(
                paddingLocal,
                document
        );
    }

    private void addInfo(PDPageContentStream contentStream, Color color, int fontSize, int xCoordinate, int yCoordinate, String targetText)
            throws IOException {
        if (yCoordinate < 20) {
            throw new IllegalPageCoordinate(String.format("%s_WARN, illegal coordinate, value: %s", getClass().getSimpleName(), yCoordinate));
        }
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, fontSize);
        contentStream.setNonStrokingColor(color);
        contentStream.newLineAtOffset(xCoordinate, yCoordinate);
        contentStream.showText(targetText);
        contentStream.endText();
    }

    private void addContacts(PDDocument document,
                             PDPageContentStream contentStream,
                             int yCoordinate,
                             String text,
                             File file) throws IOException {
        contentStream.drawImage(
                PDImageXObject.createFromByteArray(document, Files.readAllBytes(Path.of(file.getPath())), file.getName()),
                X_CONST, yCoordinate, 10, 10
        );
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.newLineAtOffset(X_CONST + 15, yCoordinate);
        contentStream.showText(text);
        contentStream.endText();
    }

    public Pair<Integer, PDDocument> addWorkInformation(PDDocument document, List<WorkExperienceModel> workExperienceList,
                                                        int currentYValue) throws IOException {
        var page = currentYValue < 20 ?
                document.getPage(1) :
                document.getPage(0);
        var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        var currentValue = currentYValue < 20 ?
                800 :
                currentYValue;

        currentValue -= 20;
        addInfo(contentStream, Color.BLACK, 16, 30, currentValue, WORK_EXPERIENCE);
        currentValue -= 15;
        Pair<Integer, PDDocument> pdDocumentPair = null;
        if (currentValue < 20) {
            document.addPage(new PDPage());
            var secondPage = document.getPage(1);
            currentValue = 800;
            var secondContentStream = new PDPageContentStream(document, secondPage, PDPageContentStream.AppendMode.APPEND, false);
            pdDocumentPair = addJobTitle(document, secondContentStream, currentValue);
        } else {
            pdDocumentPair = addJobTitle(document, contentStream, currentValue);
        }

        addJobRecursive(pdDocumentPair, workExperienceList, workExperienceList.size());

        contentStream.close();
        return Pair.of(currentValue, document);
    }

    private Pair<Integer, PDDocument> addJobTitle(PDDocument document, PDPageContentStream contentStream,
                                                  int currentValue) throws IOException {
        addInfo(contentStream, Color.GRAY, 11, 30, currentValue, "Day of job");
        addInfo(contentStream, Color.GRAY, 11, 100, currentValue, "Company");
        addInfo(contentStream, Color.GRAY, 11, 400, currentValue, "Speciality");
        addInfo(contentStream, Color.GRAY, 11, 500, currentValue, "Day of dismissal");
        contentStream.close();
        currentValue -= 15;
        return Pair.of(currentValue, document);
    }

    private Pair<Integer, PDDocument> addJobRecursive(
            Pair<Integer, PDDocument> pair,
            List<WorkExperienceModel> modelList, int count
    ) throws IOException {
        if (count <= 0) {
            return null;
        }
        var model = modelList.get(count - 1);
        int y = pair.getLeft();
        var endDate = StringUtils.isEmpty(model.getEndDate().toString()) ? "nowadays" :
                model.getEndDate().toString();
        if (y < 20) {
            var documentResult = pair.getRight();
            documentResult.addPage(new PDPage());
            var stream = new PDPageContentStream(documentResult, documentResult.getPage(1),
                    PDPageContentStream.AppendMode.APPEND, false);
            addInfo(stream, Color.BLACK, 12, 30, y, model.getStartDate().toString());
            addInfo(stream, Color.BLACK, 12, 100, y, model.getCompanyName());
            addInfo(stream, Color.BLACK, 12, 400, y, model.getSpec());
            addInfo(stream, Color.BLACK, 12, 500, y, endDate);
            stream.close();
        } else {
            var documentResult = pair.getRight();
            var stream = new PDPageContentStream(documentResult, documentResult.getPage(0),
                    PDPageContentStream.AppendMode.APPEND, false);
            addInfo(stream, Color.BLACK, 12, 30, y, model.getStartDate().toString());
            addInfo(stream, Color.BLACK, 12, 100, y, model.getCompanyName());
            addInfo(stream, Color.BLACK, 12, 400, y, model.getSpec());
            addInfo(stream, Color.BLACK, 12, 500, y, endDate);
            stream.close();
        }
        y -= 15;
        pair = Pair.of(y, pair.getValue());
        count -= 1;
        return addJobRecursive(pair, modelList, count);
    }
}
