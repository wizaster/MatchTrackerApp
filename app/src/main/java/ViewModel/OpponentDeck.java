package ViewModel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.emporium.matchtrackerappv2.R;

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

        rbNewDeck = (RadioButton) findViewById(R.id.rb_new_deck);
        rbExistingDeck = (RadioButton)findViewById(R.id.rb_existing_deck);
        spnArchetype = (Spinner)findViewById(R.id.spn_new_deck_archetype);
        spnExistingDeck = (Spinner)findViewById(R.id.spn_choose_deck);
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
}
