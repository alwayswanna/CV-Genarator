import 'package:dart_json_mapper/dart_json_mapper.dart';

import 'education_level.dart';

@jsonSerializable
class EducationInfoModel{

  @JsonProperty(name: "start")
  final String startDate;
  @JsonProperty(name: "end")
  final String endDate;
  @JsonProperty(name: "name-of-university")
  final String universityName;
  @JsonProperty(name: "level")
  final EducationLevel level;

  EducationInfoModel(
      this.startDate, this.endDate, this.universityName, this.level);

  @override
  String toString() {
    var enumName = level.name;
    return '{'
        '"start": "$startDate",'
        '"end": "$endDate",'
        '"name-of-university": "$universityName",'
        '"level": "$enumName"}';
  }
}
