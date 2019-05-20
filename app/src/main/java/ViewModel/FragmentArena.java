package ViewModel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emporium.matchtrackerappv2.R;

import java.util.ArrayList;

import Database.DatabaseHelper;
import Model.RecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArena extends Fragment {

    public static final String TAG = "Fragment Arena";

    private RecyclerView recyclerView;
    private ArrayList<String> deckNames;
    private ArrayList<String> deckImages;
    private DatabaseHelper dbh;



    public FragmentArena() {
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

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerViewDecks);

        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(deckImages, deckNames, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rvAdapter);

        //initRecyclerView(rootView);
        return rootView;
    }

    private void initValues() {

        int nbRows = dbh.getNbRows();
        Log.d(TAG, "initValues: nbrows -----------" + nbRows);
        for (int i = 0; i < nbRows; i++) {
            int deckId = dbh.getId(i);
            String name = dbh.getDeckName(deckId);
            String imageName = dbh.getImageName(name);
            deckNames.add(name);
            deckImages.add(imageName);
        }
        for(int i = 0; i < nbRows; i++){
            Log.d(TAG, "initValues: " + deckNames.get(i) + "\n");
        }

    }
    public void initRecyclerView(View rootView) {


    }

}
