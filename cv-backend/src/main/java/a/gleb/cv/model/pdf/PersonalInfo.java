package a.gleb.cv.model.pdf;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonalInfo {

    private String firstName;
    private String surname;
    private String profession;
    private String livingPlace;
    private boolean isReadyToRemoteWork;
    private LocalDate birthDate;
    private double experience;
    private int salary;
    private String phoneNumber;
    private String email;
    private String telegramLink;
    private String linkedInLink;
    private String faceBookLink;
    private String githubLink;
    private String gitlabLink;
}
