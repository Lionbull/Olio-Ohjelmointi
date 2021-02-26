package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    TextView text;
    EditText inputText;
    EditText textSpace;
    EditText fileName;
    String inputtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        text = (TextView) findViewById(R.id.textView);
        inputText = (EditText) findViewById(R.id.inputText);
        textSpace = (EditText) findViewById(R.id.fileEditInput);
        fileName = (EditText) findViewById(R.id.fileNameInput);
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputtext = inputText.getText().toString();
                text.setText(inputtext);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void hello(View v){
        System.out.println("Hello World!");
        inputtext = inputText.getText().toString();
        text.setText(inputtext);
    }
    public void loadFile(View v){
        try {
            FileInputStream fis = null;
            fis = openFileInput(fileName.getText().toString());
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine())!=null){
                sb.append(text).append("\n");
            }
            textSpace.setText(sb.toString());
            fis.close();
        } catch (IOException e){
            Log.e("IOException","Virhe syötteessä");
        }
    }
    public void saveFile(View v){
        String name = fileName.getText().toString();
        String content = textSpace.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(name,MODE_PRIVATE);
            fos.write(content.getBytes());
            textSpace.getText().clear();
        } catch (IOException e){
            Log.e("IOException","Virhe syötteessä");
        }finally{
            if (fos != null){
                try {
                    fos.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                }
            }
        }
}