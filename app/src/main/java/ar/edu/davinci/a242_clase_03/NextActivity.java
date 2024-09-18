package ar.edu.davinci.a242_clase_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.logging.Logger;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        TextView secondText = findViewById(R.id.secondText);

        if(intent.hasExtra("newText")) {
            String newText = intent.getStringExtra("newText");
            secondText.setText(newText);
        } else {
            secondText.setText(getText(R.string.next_text));
        }
    }
}