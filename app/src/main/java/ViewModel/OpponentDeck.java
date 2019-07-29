package ViewModel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.emporium.matchtrackerappv2.R;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHelper;

public class OpponentDeck extends AppCompatActivity {


    public static final String TAG = "opp deck: ----";
    DatabaseHelper dbh;
    RadioButton rbNewDeck, rbExistingDeck;
    CheckBox cbWhite, cbBlue, cbBlack, cbRed, cbGreen, cbColorless;
    Spinner spnExistingDeck, spnArchetype;
    EditText newDeckName;
    Button btnAdd, btnCancel;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_opponent_deck);
        Log.d(TAG, "onCreate: " + "-----------");
        dbh = new DatabaseHelper(getApplicationContext(), null, 1);

        initSpinners();

        rbNewDeck = (RadioButton) findViewById(R.id.rb_new_deck);
        rbExistingDeck = (RadioButton)findViewById(R.id.rb_existing_deck);
        cbBlack = (CheckBox)findViewById(R.id.cbx_black);
        cbBlue = (CheckBox)findViewById(R.id.cbx_blue);
        cbColorless = (CheckBox)findViewById(R.id.cbx_colorless);
        cbRed = (CheckBox)findViewById(R.id.cbx_red);
        cbGreen = (CheckBox)findViewById(R.id.cbx_green);
        cbWhite = (CheckBox)findViewById(R.id.cbx_white);
        newDeckName = (EditText)findViewById(R.id.new_deck_name_input);
        btnAdd = (Button)findViewById(R.id.btn_opp_deck_add);
        btnCancel = (Button)findViewById(R.id.btn_opp_deck_cancel);


    }
    public void initSpinners(){
        ArrayList<String> deckName = new ArrayList<>();
        ArrayList<String> archetypeName = dbh.getArchetypesName();
        for (int i = 0; i < dbh.getNbRows(); i++) {
            int deckId = dbh.getId(i);
            if(dbh.getFormat(deckId) == 1) {
                deckName.add(dbh.getDeckName(deckId));
            }
        }
        ArrayAdapter<String> deckAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, deckName);
        deckAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnExistingDeck = (Spinner)findViewById(R.id.spn_choose_deck);
        spnExistingDeck.setAdapter(deckAdapter);
        ArrayAdapter<String> archetypeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, archetypeName);
        archetypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnArchetype = (Spinner)findViewById(R.id.spn_new_deck_archetype);
        spnArchetype.setAdapter(archetypeAdapter);
    }
}
