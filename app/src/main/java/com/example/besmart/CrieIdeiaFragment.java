package com.example.besmart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.besmart.models.ModelClass_Ideia;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;


public class CrieIdeiaFragment extends Fragment {

    private EditText tituloIdeia;
    private EditText descripIdeia;
    private MaterialButton save;

    public CrieIdeiaFragment() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_crie_ideia, container, false);

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        FirebaseAuth auth  = FirebaseAuth.getInstance();

        tituloIdeia = v.findViewById(R.id.titlo_text);
        descripIdeia = v.findViewById(R.id.edit_descrip);
        save = v.findViewById(R.id.button_save_ideia);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date data = new Date();

                if(tituloIdeia.getText().toString().isEmpty()) {
                    tituloIdeia.requestFocus();
                    tituloIdeia.setError("Campo obrigatório");
                }else if(descripIdeia.getText().toString().isEmpty()){
                    descripIdeia.requestFocus();
                    descripIdeia.setError("Campo obrigatório");
                }else{

                    ModelClass_Ideia ideia = new ModelClass_Ideia(tituloIdeia.getText().toString(),descripIdeia.getText().toString(),"Disponivel",data.getTime());

                    //pensar melhor e um ID para a Ideia PodeSer O nomeDo Usuario+OnomeDaIdeia
                    fb.collection("User").document(auth.getCurrentUser().getUid()).collection("Ideias").document(ideia.getTitulo()).set(ideia)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getContext(), "Ideia Salva", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error ao salvar Ideia", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return v;
    }
}