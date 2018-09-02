package com.example.kproductions.dailypsalm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class MyTextToSpeech extends android.speech.tts.TextToSpeech {


    Activity context;
    public int MY_DATA_CHECK_CODE = 0;
    OnInitListener listener;
    Intent chechTts;
    TextToSpeech myTTS;

    public MyTextToSpeech(Activity context, OnInitListener listener) {
        super(context, listener);

        this.context = context;
        this.listener = listener;
        this.chechTts = new Intent();
        myTTS = this;

        chechTts.setAction(Engine.ACTION_CHECK_TTS_DATA);
        //context.startActivityForResult(chechTts,MY_DATA_CHECK_CODE);

    }

    public TextToSpeech getMyTTS() {
        return myTTS;
    }

    public Intent getInent(){
        //chechTts.setAction(Engine.ACTION_CHECK_TTS_DATA);
        return chechTts;
    }

    public void speakWords(String string){
        speak(string,QUEUE_FLUSH,null,"3");
    }
//    public void speakWords(String string){
//        speak(string,TextToSpeech.QUEUE_ADD,null);
//    }



    public void onInit(int initStatus){
        if (initStatus == android.speech.tts.TextToSpeech.SUCCESS) {
            if(isLanguageAvailable(Locale.US)== android.speech.tts.TextToSpeech.LANG_AVAILABLE)
                setLanguage(Locale.US);
        }
        else if (initStatus == android.speech.tts.TextToSpeech.ERROR) {
            Toast.makeText(context, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    public  void ttsStop(){
        stop();
    }


    public void ttsSetPitch(float val){
        setPitch(val);
    }
    public void ttsSpeed(float val){
        setSpeechRate(val);
    }





}
