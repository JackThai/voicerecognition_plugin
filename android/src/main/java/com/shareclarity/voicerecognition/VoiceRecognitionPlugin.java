package com.shareclarity.voicerecognition;

import android.app.Activity;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** VoiceRecognitionPlugin */
public class VoiceRecognitionPlugin {
  /** Plugin registration. */
  public static Activity mActivity;
  public static void registerWith(Registrar registrar) {
    mActivity = registrar.activity();

    registrar.platformViewRegistry().registerViewFactory("voice_recognition",new VoiceRecognitionFactory(registrar.messenger()));
  }
}
