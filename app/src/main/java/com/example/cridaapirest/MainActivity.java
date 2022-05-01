package com.example.cridaapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static EditText path;
    public static TextView result;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Elements in the view
        path = findViewById(R.id.editText);
        result = findViewById(R.id.textView);
        context = MainActivity.this;
    }

    public void llamada(View v) {
        if (path.getText().toString().length()>0) {
            MyATaskCliente myATaskYW = new MyATaskCliente();
            myATaskYW.execute(path.getText().toString());
        } else {
            Toast.makeText(context, "Escribe la URL", Toast.LENGTH_LONG).show();
        }
    }
}