package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import hackathon.embrapa.agrohacker.R;

public class MainScreen extends AppCompatActivity {

    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMap = (Button) findViewById(R.id.buttonToMapScreen);
    }

    public void openMap(){
        Intent intent = new Intent();
        intent.setClass(this, MapActivity.class);
        startActivity(intent);
    }
}