package com.example.besmart;

import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.besmart.models.ModelClass_Ideia;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MinhasIdeiasFragment extends Fragment {

    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fb;
    private RecyclerView rc;
    private FirestoreRecyclerAdapter adapter;
    private Query query;


    public MinhasIdeiasFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_minhas_ideias, container, false);

        FirebaseFirestore fbaa = FirebaseFirestore.getInstance();

        rc = v.findViewById(R.id.recycle_minhas_ideias);

          Query queryReborn = fbaa.collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Ideias");

        FirestoreRecyclerOptions <ModelClass_Ideia> options = new FirestoreRecyclerOptions.Builder<ModelClass_Ideia>().setQuery(queryReborn,ModelClass_Ideia.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<ModelClass_Ideia, IdeiasViewHolder>(options) {
            @NonNull
            @Override
            public IdeiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_minhas_ideias,parent,false);

                return new IdeiasViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull IdeiasViewHolder holder, int position, @NonNull ModelClass_Ideia model) {
                holder.nameIdeia.setText(model.getTitulo());
            }
        };

         rc.setHasFixedSize(true);
         rc.setLayoutManager(new LinearLayoutManager(getContext()));
         rc.setAdapter(adapter);

        return v;
    }

    private class IdeiasViewHolder extends RecyclerView.ViewHolder{
        private TextView nameIdeia;
        public IdeiasViewHolder(@NonNull View itemView) {
            super(itemView);

            nameIdeia = itemView.findViewById(R.id.text_item_minhas_ideias);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}