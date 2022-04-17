package a.gleb.cv.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class RequestModel {

    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("surname")
    private String surname;

    @JsonProperty("job")
    private String profession;

    @JsonProperty("location")
    private String livingPlace;
    @JsonProperty("isReadyToRemoteWork")
    private boolean isReadyToRemoteWork;

    @JsonProperty("birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonProperty("experience-years")
    private double experience;

    @JsonProperty("salary")
    private int salary;

    @JsonProperty("contact-information")
    private ContactInfoModel contactInfoModel;

    @JsonProperty("professional-skills")
    private List<String> professionalSkills;

    @JsonProperty("education")
    private List<EducationInfoModel> educationInfoModelList;

    @JsonProperty("experience")
    private List<WorkExperienceModel> workExperienceList;
}
