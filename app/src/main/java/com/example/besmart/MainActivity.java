package com.example.besmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besmart.models.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView register;
    private EditText email;
    private EditText senha;
    private MaterialButton entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.button_cadastrese);
        email = findViewById(R.id.email_field_home);
        senha = findViewById(R.id.password_field_home);
        entrar = findViewById(R.id.button_save_home);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().isEmpty()){
                    email.requestFocus();
                    email.setError("Email Obrigatório");
                }else if(senha.getText().toString().isEmpty()){
                    senha.requestFocus();
                    senha.setError("Senha Obrigatória");
                }else {

                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Intent intent = new Intent(getApplicationContext(),ActivityNavi.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(MainActivity.this, "Email ou Senha Incorretos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MainActivity.this, "Erro ao Logar", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(openRegister);
            }
        });
    }
}