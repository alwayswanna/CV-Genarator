package a.gleb.cv.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactInfoModel {
    @JsonProperty("phone")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("tg")
    private String telegramLink;
    @JsonProperty("linkedIn")
    private String linkedInLink;
    @JsonProperty("facebook")
    private String faceBookLink;
    @JsonProperty("github")
    private String githubLink;
    @JsonProperty("gitlab")
    private String gitlabLink;
}
