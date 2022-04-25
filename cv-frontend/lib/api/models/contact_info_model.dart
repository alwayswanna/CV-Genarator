import 'package:dart_json_mapper/dart_json_mapper.dart';

@jsonSerializable
class ContactInfoModel {
  @JsonProperty(name: "phone")
  final String phoneNumber;
  @JsonProperty(name: "email")
  final String email;
  @JsonProperty(name: "tg")
  final String telegramLink;
  @JsonProperty(name: "linkedIn")
  final String linkedInLink;
  @JsonProperty(name: "facebook")
  final String faceBookLink;
  @JsonProperty(name: "github")
  final String githubLink;
  @JsonProperty(name: "gitlab")
  final String gitlabLink;

  ContactInfoModel(this.phoneNumber, this.email, this.telegramLink,
      this.linkedInLink, this.faceBookLink, this.githubLink, this.gitlabLink);

  @override
  String toString() {
    return '{'
        '"phone": "$phoneNumber",'
        '"email": "$email", '
        '"tg": "$telegramLink", '
        '"linkedIn": "$linkedInLink", '
        '"facebook": "$faceBookLink", '
        '"github": "$githubLink", '
        '"gitlab": "$gitlabLink"}';
  }
}
