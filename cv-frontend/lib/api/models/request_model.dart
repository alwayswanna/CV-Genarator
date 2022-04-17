import 'dart:core';

import 'package:cv_frontend/api/models/work_experience_model.dart';
import 'package:dart_json_mapper/dart_json_mapper.dart';

import 'contact_info_model.dart';
import 'education_info_model.dart';

@jsonSerializable
class RequestModel {
  @JsonProperty(name: "firstname")
  final String firstName;
  @JsonProperty(name: "surname")
  final String surname;
  @JsonProperty(name: "job")
  final String profession;
  @JsonProperty(name: "location")
  final String livingPlace;
  @JsonProperty(name: "isReadyToRemoteWork")
  final bool isReadyToRemoteWork;
  @JsonProperty(name: "birthdate")
  final String birthDate;
  @JsonProperty(name: "experience-years")
  final double experience;
  @JsonProperty(name: "salary")
  final int salary;
  @JsonProperty(name: "contact-information")
  final ContactInfoModel contactInfoModel;
  @JsonProperty(name: "professional-skills")
  final List<String> professionalSkills;
  @JsonProperty(name: "education")
  final List<EducationInfoModel> educationInfoModelList;
  @JsonProperty(name: "experience")
  final List<WorkExperienceModel> workExperienceList;

  RequestModel(
      this.firstName,
      this.surname,
      this.profession,
      this.livingPlace,
      this.isReadyToRemoteWork,
      this.birthDate,
      this.experience,
      this.salary,
      this.contactInfoModel,
      this.professionalSkills,
      this.educationInfoModelList,
      this.workExperienceList);

  @override
  String toString() {
    return '{'
        '"firstname": "$firstName",'
        '"surname": "$surname", '
        '"job": "$profession", '
        '"location": "$livingPlace", '
        '"isReadyToRemoteWork": $isReadyToRemoteWork, '
        '"birthdate": "$birthDate", '
        '"experience-years": $experience, '
        '"salary": $salary, '
        '"contact-information": $contactInfoModel, '
        '"professional-skills": $professionalSkills, '
        '"education": $educationInfoModelList, '
        '"experience": $workExperienceList}';
  }
}
