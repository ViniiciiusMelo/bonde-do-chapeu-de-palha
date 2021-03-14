package com.example.besmart.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.besmart.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FeedAdapter extends FirestoreRecyclerAdapter<ModelClass_Ideia, FeedAdapter.IdeiasViewHolder> {


    public FeedAdapter(@NonNull FirestoreRecyclerOptions<ModelClass_Ideia> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull IdeiasViewHolder holder, int position, @NonNull ModelClass_Ideia model) {

        holder.nameIdeia.setText(model.getTitulo());
        holder.descripIdeia.setText(model.getDescricao());
        holder.nomeUser.setText("@Vinicius");

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
