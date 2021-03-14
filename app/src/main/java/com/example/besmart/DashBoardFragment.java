package com.example.besmart;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besmart.models.ModelClass_User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class DashBoardFragment extends Fragment {

    private TextView numeroIdeias ;
    private TextView nomeUsuario;
    private TextView emailusuario;
    private TextView usuarioNomeSet;

    public DashBoardFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v =  inflater.inflate(R.layout.fragment_dash_board, container, false);

        nomeUsuario = v.findViewById(R.id.nome_do_usuario_aqui);
        emailusuario = v.findViewById(R.id.usuario_email_get);
        usuarioNomeSet = v.findViewById(R.id.usuario_nome_set);


        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        FirebaseAuth fba = FirebaseAuth.getInstance();

        DocumentReference  doc = fb.collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    ModelClass_User user = documentSnapshot.toObject(ModelClass_User.class);
                    nomeUsuario.setText(user.getName());
                    emailusuario.setText(user.getEmail());
                    usuarioNomeSet.setText(user.getName());

                    Toast.makeText(getContext(), "UsuarioEncontrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error ao encontrar Usuario", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        return v;
    }
}