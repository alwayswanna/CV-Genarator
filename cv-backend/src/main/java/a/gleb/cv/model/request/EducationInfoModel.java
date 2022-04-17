package a.gleb.cv.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EducationInfoModel {

    @JsonProperty("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonProperty("end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @JsonProperty("name-of-university")
    private String universityName;
    @JsonProperty("level")
    private EducationLevel level;

    public enum EducationLevel {
        MAGISTRACY("magistracy"), BACHELOR("bachelor"), SPECIALTY("specialty");

        public final String description;

        private EducationLevel(String description) {
            this.description = description;
        }
    }
}

