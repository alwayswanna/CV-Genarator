import 'dart:developer';
import 'package:file_saver/file_saver.dart';

import 'package:cv_frontend/api/models/request_model.dart';
import 'package:dio/dio.dart';
import 'package:file_picker/file_picker.dart';

class RestUtils {
  final Dio dio = Dio();

  Future<String> generateCV(RequestModel model, String filename, FilePickerResult photo) async {
    var filePhoto = photo.files.first;
    var bytesList = filePhoto.bytes!.toList();
    var fileName = filePhoto.name;
    if (bytesList.isEmpty) {
      throw Exception('bytesList is Empty or Null');
    }
    FormData formData = FormData.fromMap({
      "file":
      MultipartFile.fromBytes(bytesList, filename: fileName),
      "model": model.toString()
    });
    dio.options.headers['Access-Control-Allow-Origin'] = "*";
    dio.options.headers['Content-Type'] = "multipart/form-data; charset=UTF-8";
    var response = await dio.post("http://localhost:8090/api/v1/cv-gen", data: formData, options: Options(
        responseType: ResponseType.bytes));
    log(response.data.toString());
    String path = await FileSaver.instance.saveFile(
        "result_cv.pdf",
        response.data,
        "pdf");
    return "Success, file path: " + path;
  }
}
