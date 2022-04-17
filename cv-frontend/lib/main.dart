import 'package:cv_frontend/view/input_form.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Resume generator',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: const MyHomePage(title: 'CV-GENERATOR'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("CV-GENERATOR"),
      ),
      body: Row(
        children: [
          Expanded(
              child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: const [
              Padding(
                  padding: EdgeInsets.all(6),
                  child: Text(
                    "Create CV: ",
                    style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                  )),
              MyStatefulWidget()
            ],
          )),
          Expanded(
              child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Padding(
                  padding: EdgeInsets.all(6.0),
                  child: Text(
                    "Example: ",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                    ),
                  )),
              Padding(
                padding: const EdgeInsets.all(3),
                child: Image.asset(
                  "assets/images/cv-example.jpg",
                  fit: BoxFit.cover,
                  scale: 2,
                ),
              ),
            ],
          ))
        ],
      ),
    );
  }
}
