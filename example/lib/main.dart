import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:voice_recognition/voice_recognition.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String resultString;
  VoiceRecognitionController controller;
  @override
  void initState() {
    super.initState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            body: Stack(
              alignment: FractionalOffset.center,
              children: <Widget>[
                VoiceRecognition(
                  onVoiceRecognitionCreated: _onVoiceRecognitionCreated,
                ),
                Text(
                  resultString != null ? resultString : '',
                  style: TextStyle(
                      color: Colors.red,
                      fontSize: 20.0
                  ),
                )

              ],
            )
        )
    );
  }

  void _onVoiceRecognitionCreated(VoiceRecognitionController _controller) {
    controller = _controller;
    controller.setHandler(_handeler);
    controller.startListening();
  }

  Future<dynamic> _handeler(MethodCall call) async {
    switch(call.method) {
      case "voice.result":
        debugPrint(call.arguments);
        setState(() {
          resultString = call.arguments;
        });
        break;
      case "voice.permission":
        debugPrint(call.arguments);
        setState(() {
          if (call.arguments == false) {
            resultString = "Permission Denied";
          }
        });
        break;
      default:
        break;
    }
    return 0;
  }
}
