import 'package:dart_json_mapper/dart_json_mapper.dart';

@jsonSerializable
class WorkExperienceModel {
  @JsonProperty(name: "start")
  final String startDate;
  @JsonProperty(name: "end")
  final String endDate;
  @JsonProperty(name: "name-of-company")
  final String companyName;
  @JsonProperty(name: "name-of-specialization")
  final String spec;

  WorkExperienceModel(
      this.startDate, this.endDate, this.companyName, this.spec);

  @override
  String toString() {
    return '{'
        '"start": "$startDate",'
        '"end": "$endDate",'
        '"name-of-company": "$companyName",'
        '"name-of-specialization": "$spec"}';
  }
}
