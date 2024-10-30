package ar.edu.davinci.a242_clase_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public void download() {
        ImageView imageView = findViewById(R.id.imageView);
        ImageDownloader downloader = new ImageDownloader(imageView);
        downloader.execute(getString(R.string.panda_image));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            setContentView(R.layout.activity_main);
            download();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra("newText", getString(R.string.new_text));
        startActivity(intent);
    }
}