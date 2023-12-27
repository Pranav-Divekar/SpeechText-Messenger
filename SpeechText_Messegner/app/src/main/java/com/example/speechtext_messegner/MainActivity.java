package com.example.speechtext_messegner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.speechtext_messegner.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    EditText num,txt;
    TextToSpeech ts ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.no);
        txt = findViewById(R.id.txt);
        ts = new TextToSpeech(this,this);
    }
    public void onInit(int i) {
        if(i==TextToSpeech.SUCCESS)
        {
            int result = ts.setLanguage(Locale.UK);
        }
        else
        {
            Toast.makeText(this, "Not Supported", Toast.LENGTH_SHORT).show();
        }
    }
    public void Speak(View v)
    {
        ts.speak(txt.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);
    }
    public void send(View v)
    {
        SmsManager sm = SmsManager.getDefault();
        try {
            sm.sendTextMessage(num.getText().toString(),null,txt.getText().toString(),null,null);
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Cannot Send Message",Toast.LENGTH_LONG).show();
        }
    }
}