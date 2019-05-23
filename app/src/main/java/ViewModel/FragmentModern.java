package ViewModel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emporium.matchtrackerappv2.R;

import java.util.ArrayList;

import Database.DatabaseHelper;
import Model.DeckId;
import Model.RecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentModern extends Fragment {

public static final String TAG = "Fragment modern";


    private RecyclerView recyclerView;
    private ArrayList<DeckId> deckNames;
    private ArrayList<String> deckImages;
    private DatabaseHelper dbh;



    public FragmentModern() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbh = new DatabaseHelper(getContext(), null, 1);
        deckNames = new ArrayList<>();
        deckImages = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initValues();

        RecyclerViewAdapter.Listener listener = new RecyclerViewAdapter.Listener() {
            @Override
            public void onItemClicked(int id) {
                Intent deckDetails = new Intent(getActivity(), DeckDetails.class);
                deckDetails.putExtra("deck_id", id);
                getActivity().startActivity(deckDetails);
            }
        };
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewDecks);

        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(deckImages, deckNames, getActivity(), listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rvAdapter);


        return rootView;
    }
    private void initValues() {

        int nbRows = dbh.getNbRows();
        Log.d(TAG, "modern: nbrows -----------" + nbRows);
        for (int i = 0; i < nbRows; i++) {
            int deckId = dbh.getId(i);
            if(dbh.getFormat(deckId) == 1) {
                String name = dbh.getDeckName(deckId);
                String imageName = dbh.getImageName(name);
                deckNames.add(new DeckId(deckId, name));
                deckImages.add(imageName);
            }
        }
    }


}
