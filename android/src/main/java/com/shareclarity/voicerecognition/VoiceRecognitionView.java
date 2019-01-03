package com.shareclarity.voicerecognition;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.shareclarity.voicerecognition.speechrecognitionview.RecognitionProgressView;
import com.shareclarity.voicerecognition.speechrecognitionview.adapters.RecognitionListenerAdapter;

import java.util.HashMap;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

public class VoiceRecognitionView  implements PlatformView, MethodChannel.MethodCallHandler, RecognitionListener {
    public final MethodChannel methodChannel;
    private Context context;
    private View mView;
    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    //Speech Recognition
    private SpeechRecognizer speech;
    String transcription = "";
    private boolean cancelled = false;
    private Intent recognizerIntent;

    VoiceRecognitionView(Context _context, BinaryMessenger messenger, int id, Object object) {
        context = _context;
        methodChannel = new MethodChannel(messenger, "voice_recognition_" + id);
        methodChannel.setMethodCallHandler(this);

        //Speech Recognition
//        speech = SpeechRecognizer.createSpeechRecognizer(VoiceRecognitionPlugin.mActivity.getApplicationContext());
//        speech.setRecognitionListener(this);
//        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        this.activityLifecycleCallbacks =
                new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }

                };
    }

    @Override
    public View getView() {
        int[] colors = {
                android.R.color.holo_red_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_red_dark
        };

        int[] heights = { 20, 24, 18, 23, 16 };


        LayoutInflater inflater = LayoutInflater.from(VoiceRecognitionPlugin.mActivity);

        final View view = inflater.inflate(R.layout.speech_layout, null);
        final RecognitionProgressView recognitionProgressView = view.findViewById(R.id.recognition_view);
//        recognitionProgressView.setSpeechRecognizer(speech);
        recognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onResults(Bundle results) {
//                showResults(results);
            }
        });
        recognitionProgressView.setColors(colors);
        recognitionProgressView.setBarMaxHeightsInDp(heights);
        recognitionProgressView.setCircleRadiusInDp(2);
        recognitionProgressView.setSpacingInDp(2);
        recognitionProgressView.setIdleStateAmplitudeInDp(2);
        recognitionProgressView.setRotationRadiusInDp(10);
//        recognitionProgressView.play();

        return view;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {

            default:
                result.notImplemented();
        }
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
