package viewmodel;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emporium.matchtrackerappv2.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import database.DatabaseHelper;
import model.DeckId;
import model.RecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStandard extends Fragment {

public static final String TAG = "Fragment Standard";


    private ArrayList<DeckId> deckNames;
    private ArrayList<String> deckImages;
    private DatabaseHelper dbh;

    public FragmentStandard() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbh = new DatabaseHelper(getContext(), null, 1);
        deckNames = new ArrayList<>();
        deckImages = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initValues();

        RecyclerViewAdapter.Listener listener = new RecyclerViewAdapter.Listener(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClicked(int id) {
                Intent deckDetails = new Intent(getActivity(), DeckDetails.class);
                deckDetails.putExtra("deck_id", id);
                Objects.requireNonNull(getActivity()).startActivity(deckDetails);
            }
        };
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewDecks);

        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(deckImages, deckNames, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rvAdapter);



        return rootView;
    }

    private void initValues() {

        int nbRows = dbh.getNbRows();
        Log.d(TAG, "Standard: nbrows -----------" + nbRows);
        for (int i = 0; i < nbRows; i++) {
            int deckId = dbh.getId(i);
            if(dbh.getFormat(deckId) == 2) {
                String name = dbh.getDeckName(deckId);
                String imageName = dbh.getImageName(name);
                deckNames.add(new DeckId(deckId, name));
                deckImages.add(imageName);
            }
        }
    }
}
