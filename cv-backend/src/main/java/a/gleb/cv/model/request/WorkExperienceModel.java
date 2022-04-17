package a.gleb.cv.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class WorkExperienceModel {

    @JsonProperty("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    @JsonProperty("end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;
    @JsonProperty("name-of-company")
    private String companyName;
    @JsonProperty("name-of-specialization")
    private String spec;
}
