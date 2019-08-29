package viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emporium.matchtrackerappv2.R;

import database.DatabaseHelper;
import model.RecyclerViewAdapter;

public class DeckDetails extends AppCompatActivity {
    //todo onstop, onresume...

    public static final String TAG = "Deck details";
    DatabaseHelper dbh;
    ViewPager viewPager;
    RecyclerView recyclerView;
    TextView deckNameTitle, deckFormat, winRate, nbGame, aggroWr, midrangeWr, controlWr, tempoWr, bigManaWr, comboWr;
    Button btnAddMatch, btnHistory, btnChooseOppDeck;

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: DeckDetails--------");
        setContentView(R.layout.activity_deck_details);
        //viewPager = (ViewPager)findViewById(R.id.deck_details_viewpager);
        viewPager = new ViewPager(this);
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
        aggroWr = (TextView)findViewById(R.id.aggro_wr);
        tempoWr = (TextView)findViewById(R.id.tempo_wr);
        controlWr = (TextView)findViewById(R.id.control_wr);
        midrangeWr = (TextView)findViewById(R.id.midrange_wr);
        comboWr = (TextView)findViewById(R.id.combo_wr);
        bigManaWr = (TextView)findViewById(R.id.big_mana_wr);
        btnAddMatch = (Button)findViewById(R.id.btn_add_the_match);
        btnHistory = (Button)findViewById(R.id.btn_history);
        btnChooseOppDeck = (Button)findViewById(R.id.btn_deck_choice);

        initValues(deckId);

    }

    public void initValues(int id){
        deckNameTitle.setText(dbh.getDeckName(id));
        deckFormat.setText(dbh.getFormatName(id));
        winRate.setText(Double.toString(dbh.getWinRate(id)) + "%");
        nbGame.setText(Integer.toString(dbh.getNbGames(id)));
        aggroWr.setText(dbh.getWinRateByArch(id, 1));
        tempoWr.setText(dbh.getWinRateByArch(id, 3));
        controlWr.setText(dbh.getWinRateByArch(id, 2));
        midrangeWr.setText(dbh.getWinRateByArch(id, 4));
        comboWr.setText(dbh.getWinRateByArch(id, 6));
        bigManaWr.setText(dbh.getWinRateByArch(id, 5));
        btnAddMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnChooseOppDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOppDeck();
            }
        });

    }
    public void startOppDeck(){
        Intent oppDeck = new Intent(this, OpponentDeck.class);
        startActivity(oppDeck);
    }
}
