package ar.edu.davinci.a242_clase_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void download() {
        ImageView imageView = findViewById(R.id.imageView);
        ImageDownloader downloader = new ImageDownloader(imageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        download();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra("newText", getString(R.string.new_text));
        startActivity(intent);
    }
}