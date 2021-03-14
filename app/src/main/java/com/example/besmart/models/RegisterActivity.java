package com.example.besmart.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.besmart.MainActivity;
import com.example.besmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private MaterialButton save;
    private ImageView imageUser;
    private EditText emailUser;
    private EditText senhaUser;
    private EditText nomeUser;
    private MaterialButton addPhoto;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference sr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        save = findViewById(R.id.button_save);
        emailUser = findViewById(R.id.email_field);
        senhaUser = findViewById(R.id.password_field);
        nomeUser = findViewById(R.id.name_field);

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        sr = storage.getReference();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nomeUser.getText().toString().isEmpty()){
                    nomeUser.requestFocus();
                    nomeUser.setError("Nome Obrigatório");
                }else if(emailUser.getText().toString().isEmpty()){
                    emailUser.requestFocus();
                    emailUser.setError("Email Obrigatório");
                }else if(senhaUser.getText().toString().isEmpty()){
                    senhaUser.requestFocus();
                    senhaUser.setError("Senha Obrigatório");
                } else {

            mAuth.createUserWithEmailAndPassword(emailUser.getText().toString(),senhaUser.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                        ModelClass_User user = new ModelClass_User(nomeUser.getText().toString(),emailUser.getText().toString(),"Usuario",0,0,"TesteLink");
                        fb.collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(RegisterActivity.this, "Falha ao Cadastrar-se", Toast.LENGTH_SHORT).show();

                }
            });
                }
            }
        });
    }

    private void pegaFoto() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && requestCode == RESULT_OK && data!= null && data.getData() !=null ){
            imageUri = data.getData();
            imageUser.setImageURI(imageUri);
            UploadImage();
        }
    }

    private void UploadImage() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riverRef = sr.child("image/"+randomKey);

        riverRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                pd.dismiss();
                Snackbar.make(findViewById(R.id.container_registro),"Image Uploaded",Snackbar.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.00 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());

            }
        });
    }
}