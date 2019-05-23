package ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.emporium.matchtrackerappv2.R;

import Database.DatabaseHelper;
import Model.RecyclerViewAdapter;

public class DeckDetails extends AppCompatActivity {
    //todo onstop, onresume...

    public static final String TAG = "Deck details";
    DatabaseHelper dbh;
    ViewPager viewPager;
    RecyclerView recyclerView;
    TextView deckNameTitle, deckFormat, winRate, nbGame, aggroWr, midrangeWr, controlWr, tempoWr, bigManaWr, comboWr;

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: DeckDetails--------");
        setContentView(R.layout.activity_deck_details);
        viewPager = (ViewPager)findViewById(R.id.deck_details_viewpager);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewDecksDetails);
        dbh = new DatabaseHelper(getApplicationContext(), null, 1);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter();
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(rvAdapter);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int deckId = extras.getInt("deck_id");
        Log.d(TAG, "onCreate: " + deckId);


        deckFormat = (TextView)findViewById(R.id.deck_detail_format);
        deckNameTitle = (TextView)findViewById(R.id.deck_detail_name);
        winRate = (TextView)findViewById(R.id.winRate_overall);
        nbGame = (TextView)findViewById(R.id.deck_detail_game_played);

        initValues(deckId);

    }

    public void initValues(int id){
            deckNameTitle.setText(dbh.getDeckName(id));
            deckFormat.setText(dbh.getFormatName(id));
            winRate.setText(Double.toString(dbh.getWinRate(id)) + "%");
            nbGame.setText(Integer.toString(dbh.getNbGames(id)));

    }
}
