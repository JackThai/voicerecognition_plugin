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
            body: new Container(
              height: 100,
              decoration: new BoxDecoration(
                gradient: new LinearGradient(colors: [Colors.purple[500], Colors.purple[700]],
                    begin: const FractionalOffset(0.5, 0.0),
                    end: const FractionalOffset(0.0, 0.5),
                    stops: [0.0,1.0],
                    tileMode: TileMode.clamp
                ),
              ),
              child: VoiceRecognition(
                onVoiceRecognitionCreated: _onVoiceRecognitionCreated,
              ),
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
