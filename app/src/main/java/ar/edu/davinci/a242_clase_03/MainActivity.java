package ar.edu.davinci.a242_clase_03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public void download() {
        ImageView imageView = findViewById(R.id.imageView);
        ImageDownloader downloader = new ImageDownloader(imageView);
        downloader.execute(getString(R.string.panda_image));
    }

    public void getCollection () {
        db.collection("characters")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("firebase-firestore", document.getId() + " => " + document.getData().get("name"));
                        }
                    } else {
                        Log.d("firebase-firestore", "Error getting documents: ", task.getException());
                    }
                }
            });
    }

    public void addCharacter () {
        Map<String, Object> character = new HashMap<>();
        character.put("name", "Ada");
        character.put("status", "Dead");
        character.put("specie", "Human");

        db.collection("characters").add(character)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("firebase-firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("firebase-firestore", "Error adding document", e);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            setContentView(R.layout.activity_main);
            download();
            getCollection();
            //addCharacter();
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