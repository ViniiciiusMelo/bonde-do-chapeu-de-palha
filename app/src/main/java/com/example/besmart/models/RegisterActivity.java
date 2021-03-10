package com.example.besmart.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.besmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private MaterialButton save;
    private ImageView imageUser;
    private EditText emailUser;
    private EditText senhaUser;
    private EditText nomeUser;
    private MaterialButton addPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        save = findViewById(R.id.button_save);
        imageUser = findViewById(R.id.photo_field);
        emailUser = findViewById(R.id.email_field);
        senhaUser = findViewById(R.id.password_field);
        nomeUser = findViewById(R.id.name_field);
        addPhoto = findViewById(R.id.photo_button);

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
}