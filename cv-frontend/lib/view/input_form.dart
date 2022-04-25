import 'dart:developer';

import 'package:cv_frontend/api/models/contact_info_model.dart';
import 'package:cv_frontend/api/models/education_info_model.dart';
import 'package:cv_frontend/api/models/education_level.dart';
import 'package:cv_frontend/api/models/work_experience_model.dart';
import 'package:cv_frontend/api/rest_utils.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';

import '../api/models/request_model.dart';

class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key}) : super(key: key);

  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  bool isChecked = false;
  FilePickerResult? file;
  var universityFirstLevel = "UNKNOWN";
  var universitySecondLevel = "UNKNOWN";
  var universityThirdLevel = "UNKNOWN";

  static RestUtils utils = RestUtils();

  // Personal data controllers
  final firstNameController = TextEditingController();
  final surnameController = TextEditingController();
  final specializationController = TextEditingController();
  final locationController = TextEditingController();
  final experienceController = TextEditingController();
  final birthDateController = TextEditingController();
  final salaryController = TextEditingController();
  final emailController = TextEditingController();
  final phoneController = TextEditingController();
  final tgController = TextEditingController();
  final linkedInController = TextEditingController();
  final facebookController = TextEditingController();
  final githubController = TextEditingController();
  final gitlabController = TextEditingController();
  final skillsController = TextEditingController();

  // University data controller
  final startFirstUniversity = TextEditingController();
  final endFirstUniversity = TextEditingController();
  final firstUniversity = TextEditingController();
  final startSecondUniversity = TextEditingController();
  final endSecondUniversity = TextEditingController();
  final secondUniversity = TextEditingController();
  final startThirdUniversity = TextEditingController();
  final endThirdUniversity = TextEditingController();
  final thirdUniversity = TextEditingController();

  // Company data
  final company1start = TextEditingController();
  final company1end = TextEditingController();
  final company1 = TextEditingController();
  final company1speciality = TextEditingController();
  final company2start = TextEditingController();
  final company2end = TextEditingController();
  final company2 = TextEditingController();
  final company2speciality = TextEditingController();

  void proceedRequest(
      RequestModel requestModel, FilePickerResult platformFile) {
    utils.generateCV(requestModel, "photo", platformFile);
  }

  @override
  Widget build(BuildContext context) {
    Color getColor(Set<MaterialState> states) {
      const Set<MaterialState> interactiveStates = <MaterialState>{
        MaterialState.pressed,
        MaterialState.hovered,
        MaterialState.focused,
      };
      if (states.any(interactiveStates.contains)) {
        return Colors.blue;
      }
      return Colors.green;
    }

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          children: [
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: firstNameController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'Name',
                    labelText: 'First name'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter name';
                  }
                  return null;
                },
              ),
            ),
            const SizedBox(width: 50),
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: surnameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Surname',
                  hintText: 'Surname',
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter surname';
                  }
                  return null;
                },
              ),
            ),
          ],
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 5, 0, 5),
          child: SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: specializationController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: "Specialization:"),
              )),
        ),
        Row(
          children: [
            SizedBox(
              width: 250,
              height: 40,
              child: TextField(
                controller: locationController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'Moscow',
                    labelText: "Location/City:"),
              ),
            ),
            const SizedBox(
              width: 50,
            ),
            SizedBox(
              width: 250,
              height: 40,
              child: TextField(
                controller: experienceController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: '3.5',
                    labelText: "Experience years:"),
              ),
            )
          ],
        ),
        Row(
          children: [
            const Padding(
              padding: EdgeInsets.fromLTRB(5, 0, 0, 0),
              child: Text("Is ready to remote work: "),
            ),
            Checkbox(
              checkColor: Colors.white,
              fillColor: MaterialStateProperty.resolveWith(getColor),
              value: isChecked,
              onChanged: (bool? value) {
                setState(() {
                  isChecked = value!;
                });
              },
            )
          ],
        ),
        Row(
          children: [
            SizedBox(
                width: 250,
                height: 40,
                child: TextField(
                  controller: birthDateController,
                  decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Birth date:",
                      hintText: "yyyy-MM-dd"),
                )),
            const SizedBox(width: 50),
            SizedBox(
                width: 250,
                height: 40,
                child: TextField(
                  controller: salaryController,
                  decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Salary:",
                      hintText: "3000~4000"),
                )),
          ],
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 5, 0, 5),
          child: Row(
            children: [
              SizedBox(
                width: 250,
                height: 40,
                child: TextFormField(
                  controller: emailController,
                  decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: 'xxxx@xxx.com',
                      labelText: 'Email'),
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter name';
                    }
                    return null;
                  },
                ),
              ),
              const SizedBox(width: 50),
              SizedBox(
                width: 250,
                height: 40,
                child: TextFormField(
                  controller: phoneController,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Phone number',
                    hintText: '+7XXXXXXXXX',
                  ),
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter phone';
                    }
                    return null;
                  },
                ),
              ),
            ],
          ),
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 5, 0, 5),
          child: Row(
            children: [
              SizedBox(
                width: 250,
                height: 40,
                child: TextFormField(
                  controller: tgController,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Telegram link',
                    hintText: '@some_user',
                  ),
                ),
              ),
              const SizedBox(width: 50),
              SizedBox(
                width: 250,
                height: 40,
                child: TextFormField(
                  controller: linkedInController,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'LinkedIn link',
                    hintText: 'https://....',
                  ),
                ),
              ),
            ],
          ),
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 5, 0, 5),
          child: Row(
            children: [
              SizedBox(
                width: 250,
                height: 40,
                child: TextFormField(
                  controller: facebookController,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Facebook link',
                    hintText: 'https://...',
                  ),
                ),
              ),
              const SizedBox(width: 50),
              SizedBox(
                width: 250,
                height: 40,
                child: TextFormField(
                  controller: githubController,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Github link',
                    hintText: 'https://....',
                  ),
                ),
              ),
            ],
          ),
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 5, 0, 5),
          child: SizedBox(
              width: 250,
              height: 40,
              child: TextField(
                controller: gitlabController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: "GitLab link:",
                    hintText: "https:// ... "),
              )),
        ),
        Padding(
            padding: EdgeInsets.fromLTRB(0, 5, 0, 5),
            child: SizedBox(
              width: 400,
              height: 40,
              child: TextField(
                  controller: skillsController,
                  decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Professional skills",
                      hintText:
                          '"Java 11", "Mongodb", "NodeJS", "VueJS", "etc"')),
            )),
        const Padding(
            padding: EdgeInsets.fromLTRB(5, 10, 0, 10),
            child: Text("University information:")),
        Row(
          children: [
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: startFirstUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Start date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: firstUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'University:',
                  hintText: 'Name of university.',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: endFirstUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'End date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            DropdownButton(
              items: <String>['MAGISTRACY', 'BACHELOR', 'SPECIALTY']
                  .map<DropdownMenuItem<String>>((String value) {
                return DropdownMenuItem<String>(
                  value: value,
                  child: Text(value),
                );
              }).toList(),
              onChanged: (String? value) {
                universityFirstLevel = value.toString();
              },
            )
          ],
        ),
        Row(
          children: [
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: startSecondUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Start date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: secondUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'University:',
                  hintText: 'Name of university.',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: endSecondUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'End date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            DropdownButton(
              items: <String>['MAGISTRACY', 'BACHELOR', 'SPECIALTY']
                  .map<DropdownMenuItem<String>>((String value) {
                return DropdownMenuItem<String>(
                  value: value,
                  child: Text(value),
                );
              }).toList(),
              onChanged: (String? value) {
                universitySecondLevel = value.toString();
              },
            )
          ],
        ),
        Row(
          children: [
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: startThirdUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Start date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: thirdUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'University:',
                  hintText: 'Name of university.',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: endThirdUniversity,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'End date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            DropdownButton(
              items: <String>['MAGISTRACY', 'BACHELOR', 'SPECIALTY']
                  .map<DropdownMenuItem<String>>((String value) {
                return DropdownMenuItem<String>(
                  value: value,
                  child: Text(value),
                );
              }).toList(),
              onChanged: (String? value) {
                universityThirdLevel = value.toString();
              },
            )
          ],
        ),
        const Padding(
            padding: EdgeInsets.fromLTRB(5, 10, 0, 10),
            child: Text("Work experience:")),
        Row(
          children: [
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: company1start,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Start date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: company1,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Company:',
                  hintText: 'Name of company.',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: company1end,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'End date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(
              width: 10,
            ),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: company1speciality,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Speciality:',
                  hintText: 'Speciality',
                ),
              ),
            )
          ],
        ),
        Row(
          children: [
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: company2start,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Start date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 250,
              height: 40,
              child: TextFormField(
                controller: company2,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Company:',
                  hintText: 'Name of company.',
                ),
              ),
            ),
            const SizedBox(width: 30),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: company2end,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'End date:',
                  hintText: 'yyyy-MM-dd',
                ),
              ),
            ),
            const SizedBox(
              width: 10,
            ),
            SizedBox(
              width: 150,
              height: 40,
              child: TextFormField(
                controller: company2speciality,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Speciality:',
                  hintText: 'Speciality',
                ),
              ),
            ),
          ],
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 5, 0, 5),
          child: ElevatedButton(
            onPressed: () async {
              FilePickerResult? result = await FilePicker.platform.pickFiles();
              if (result != null) {
                file = result;
              }
            },
            child: const Text('Select image'),
          ),
        ),
        Padding(
          padding: const EdgeInsets.symmetric(vertical: 16.0),
          child: ElevatedButton(
            onPressed: () {
              List<EducationInfoModel> educationList = [
                EducationInfoModel(
                    startFirstUniversity.text,
                    endFirstUniversity.text,
                    firstUniversity.text,
                    EducationLevel.values.firstWhere((e) =>
                        e.toString() ==
                        'EducationLevel.' + universityFirstLevel)),
                EducationInfoModel(
                    startSecondUniversity.text,
                    endSecondUniversity.text,
                    secondUniversity.text,
                    EducationLevel.values.firstWhere((e) =>
                        e.toString() ==
                        'EducationLevel.' + universitySecondLevel)),
                EducationInfoModel(
                    startThirdUniversity.text,
                    endThirdUniversity.text,
                    thirdUniversity.text,
                    EducationLevel.values.firstWhere((e) =>
                        e.toString() ==
                        'EducationLevel.' + universityThirdLevel))
              ];
              List<WorkExperienceModel> workList = [
                WorkExperienceModel(company1start.text, company1end.text,
                    company1.text, company1speciality.text),
                WorkExperienceModel(company2start.text, company2start.text,
                    company2.text, company2speciality.text)
              ];
              log('start proceed request');

              proceedRequest(
                  RequestModel(
                      firstNameController.text,
                      surnameController.text,
                      specializationController.text,
                      locationController.text,
                      isChecked,
                      birthDateController.text,
                      double.parse(experienceController.text),
                      int.parse(salaryController.text),
                      ContactInfoModel(
                          phoneController.text,
                          emailController.text,
                          tgController.text,
                          linkedInController.text,
                          facebookController.text,
                          githubController.text,
                          gitlabController.text),
                      skillsController.text.split(','),
                      educationList,
                      workList),
                  file!);
            },
            child: const Text('Submit'),
          ),
        ),
      ],
    );
  }
}
