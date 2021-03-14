package com.example.besmart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.besmart.models.FeedAdapter;
import com.example.besmart.models.MinhasIdeiasAdapter;
import com.example.besmart.models.ModelClass_Ideia;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

public class FeedFragment extends Fragment {

    private RecyclerView rc;
    private FeedAdapter adapter;

    public FeedFragment() {
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
        View v = inflater.inflate(R.layout.fragment_feed, container, false);

        rc = v.findViewById(R.id.recycle_feed);

        FirebaseFirestore fb = FirebaseFirestore.getInstance();

        Query consulta = fb.collectionGroup("Ideias");
        consulta.limit(100);

        FirestoreRecyclerOptions<ModelClass_Ideia> options = new FirestoreRecyclerOptions.Builder<ModelClass_Ideia>()
                .setLifecycleOwner(this)
                .setQuery(consulta,ModelClass_Ideia.class)
                .build();

         adapter = new FeedAdapter(options);

        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        rc.setAdapter(adapter);

        return v;


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