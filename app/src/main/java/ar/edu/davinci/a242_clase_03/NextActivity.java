package ar.edu.davinci.a242_clase_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        TextView secondText = findViewById(R.id.secondText);

        if(intent.hasExtra("newText")) {
            new GetRicks(secondText).execute("https://3146d3e3-7d19-4be9-8603-20a41be9f8be-00-1mzroolo06fe5.janeway.repl.co/randomCharacters");
            String newText = intent.getStringExtra("newText");
            //secondText.setText(newText);
        } else {
            secondText.setText(getText(R.string.next_text));
        }
    }
}