package com.example.besmart.models;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.besmart.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MinhasIdeiasAdapter extends FirestoreRecyclerAdapter<ModelClass_Ideia, MinhasIdeiasAdapter.IdeiasViewHolder> {


    public MinhasIdeiasAdapter(@NonNull FirestoreRecyclerOptions<ModelClass_Ideia> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull IdeiasViewHolder holder, int position, @NonNull ModelClass_Ideia model) {


        holder.nameIdeia.setText(model.getTitulo());
        holder.descripIdeia.setText(model.getDescricao());
        holder.nomeUser.setText(model.getDonoDaideia());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @NonNull
    @Override
    public IdeiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_minhas_ideias, parent, false);

        return new IdeiasViewHolder(view);
    }

    public class IdeiasViewHolder extends RecyclerView.ViewHolder {
        private TextView nameIdeia;
        private TextView nomeUser;
        private TextView descripIdeia;

        public IdeiasViewHolder(@NonNull View itemView) {
            super(itemView);

            nameIdeia = itemView.findViewById(R.id.titulo_ideia_item);
            nomeUser = itemView.findViewById(R.id.nome_do_usuario_ideia);
            descripIdeia = itemView.findViewById(R.id.descrip_ideia_item);

        }
    }
}
