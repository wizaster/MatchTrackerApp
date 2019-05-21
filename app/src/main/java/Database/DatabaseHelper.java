package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* todo refresh imports
import model.Format;

import static android.content.Context.MODE_PRIVATE;
import static com.emporium.matchtrackerapp.controller.LoginActivity.PREF_KEY_USERNAME;
*/

public class DatabaseHelper extends SQLiteOpenHelper {
    //Logcat tag
    public static final String LOG = "DatabaseHelper";
    public static final String TAG = "DatabaseHelper";
    //database version
    public static final int DATABASE_VERSION = 1;
    //Database name
    public static final String DATABASE_NAME = "MatchTracker";
    //Table names
    public static final String TABLE_PLAYERS = "Players_List";
    public static final String TABLE_DECKS = "Decks_Name";
    public static final String TABLE_ARCHETYPE = "Archetypes";
    public static final String TABLE_COLORS = "Colors";
    public static final String TABLE_FORMAT = "Format";
    public static final String TABLE_GAME_DETAILS = "Game_Details";
    public static final String TABLE_MATCH = "Matches";
    public static final String TABLE_USERS_DECK = "Users_Deck";
    //Common column name
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "Name";
    //Users_Deck table - coloummn names
    public static final String KEY_FORMAT_ID = "Format_ID";
    public static final String KEY_ARCHETYPE_ID = "Archetype_ID";
    public static final String KEY_COLORS_NAME = "Colors_Name";
    public static final String KEY_WIN_RATE = "Win_Rate";
    public static final String KEY_PLAYERS_LIST_ID = "Players_List_ID";
    //Game_Details - column names
    public static final String KEY_MATCH_ID = "match_ID";
    public static final String KEY_GAME_NO = "Game_NO";
    public static final String KEY_ON_THE_PLAY = "On_The_Play";
    public static final String KEY_WIN = "Win";
    //Matches table - column names
    public static final String KEY_ARCHETYPE_1_ID = "Archetype_1_ID";
    public static final String KEY_ARCHETYPE_2_ID = "Archetype_2_ID";
    public static final String KEY_SCORE_1 = "Score_1";
    public static final String KEY_SCORE_2 = "Score_2";
    public static final String KEY_DRAW = "Draw";
    public static final String KEY_TOTAL_GAME = "Total_Game";
    public static final String KEY_WINNER = "Winner";
    //COLORS TABLE - COLUMN NAME
    public static final String KEY_REFERENCE_IMAGE = "Reference_Image";
    //table create statement
    //TABLE_PLAYERS
    public static final String CREATE_TABLE_PLAYERS = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYERS
            +"(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, " + KEY_NAME  + " VARCHAR2(50))";
    //TABLE_DECKS
    public static final String CREATE_TABLE_DECKS = "CREATE TABLE IF NOT EXISTS " + TABLE_DECKS
            + "(" + KEY_ID +  " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45), "
            + KEY_FORMAT_ID + " INT, "
            + KEY_ARCHETYPE_ID + " INT, "
            + KEY_COLORS_NAME + " VARCHAR(45), "
            + "CONSTRAINT `fk_Deck_Name_Format1` "
            + "FOREIGN KEY (" + KEY_FORMAT_ID + ") "
            + "REFERENCES " + TABLE_FORMAT + "(" + KEY_ID + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION, "
            + "CONSTRAINT `fk_Deck_Name_Archetypes1` "
            + "FOREIGN KEY ( " + KEY_ARCHETYPE_ID + ") "
            + "REFERENCES " + TABLE_ARCHETYPE + "(" + KEY_ID + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION )";
    //TABLE_ARCHETYPE
    public static final String CREATE_TABLE_ARCHETYPE = "CREATE TABLE IF NOT EXISTS " +TABLE_ARCHETYPE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45))";
    //TABLE_COLORS
    public static final String CREATE_TABLE_COLORS = "CREATE TABLE IF NOT EXISTS " +TABLE_COLORS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45), "
            + KEY_REFERENCE_IMAGE + " VARCHAR2(45))";
    //TABLE_FORMAT
    public static final String CREATE_TABLE_FORMAT = "CREATE TABLE IF NOT EXISTS " +TABLE_FORMAT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45))";
    //TABLE_GAME_DETAILS
    public static final String CREATE_TABLE_GAME_DETAILS = "CREATE TABLE IF NOT EXISTS " + TABLE_GAME_DETAILS
            + "(" + KEY_MATCH_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + KEY_GAME_NO + " NOT NULL, "
            + KEY_ON_THE_PLAY + " TINYINT, "
            + KEY_WIN + " TINYINT, "
            + "CONSTRAINT `fk_Game_Details_Match1` "
            + "FOREIGN KEY (" + KEY_MATCH_ID + ") "
            + "REFERENCES " + TABLE_MATCH + "(" + KEY_ID + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION)";
    //TABLE_MATCH
    public static final String CREATE_TABLE_MATCH = "CREATE TABLE IF NOT EXISTS " + TABLE_MATCH
            + "( " + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_ARCHETYPE_1_ID + " INT, "
            + KEY_ARCHETYPE_2_ID + " INT, "
            + KEY_SCORE_1 + " INT, "
            + KEY_SCORE_2 + " INT, "
            + KEY_DRAW + " INT, "
            + KEY_TOTAL_GAME + " INT, "
            + KEY_WINNER + " INT, "
            + "CONSTRAINT `fk_Match_Deck_Name1` "
            + "FOREIGN KEY (" + KEY_ARCHETYPE_1_ID + ") "
            + "REFERENCES " + TABLE_DECKS + "(" + KEY_ID + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION, "
            + "CONSTRAINT `fk_Match_Deck_Name2` "
            + "FOREIGN KEY (" + KEY_ARCHETYPE_2_ID + ") "
            + "REFERENCES " + TABLE_DECKS + "(" + KEY_ID + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION)";
    //TABLE_USERS_DECK
    public static final String CREATE_TABLE_USERS_DECK = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS_DECK
            +"(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + KEY_FORMAT_ID + " VARCHAR(45),"
            + KEY_NAME + " VARCHAR(45),"
            + KEY_ARCHETYPE_ID + " VARCHAR(45),"
            + KEY_COLORS_NAME + " VARCHAR(45),"
            + KEY_WIN_RATE + " VARCHAR(45),"
            + KEY_PLAYERS_LIST_ID + " INTEGER NOT NULL,"
            + "CONSTRAINT `fk_Users_Deck_Players_List`"
            + "FOREIGN KEY (" + KEY_PLAYERS_LIST_ID + ")"
            + "REFERENCES " + TABLE_PLAYERS + "(" + KEY_ID + ")"
            +"ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION,"
            + "CONSTRAINT `fk_Users_Deck_Deck_Name1`"
            + "FOREIGN KEY (" + KEY_ID + ")"
            + "REFERENCES " + TABLE_DECKS + "(" + KEY_ID + ")"
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION)";

    public DatabaseHelper( Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    public DatabaseHelper( Context context, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, version, errorHandler);
    }
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    public void onCreate() {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS_DECK);
        db.execSQL(CREATE_TABLE_MATCH);
        db.execSQL(CREATE_TABLE_GAME_DETAILS);
        db.execSQL(CREATE_TABLE_FORMAT);
        db.execSQL(CREATE_TABLE_COLORS);
        db.execSQL(CREATE_TABLE_ARCHETYPE);
        db.execSQL(CREATE_TABLE_DECKS);
        db.execSQL(CREATE_TABLE_PLAYERS);

        Log.d(TAG, "onCreate: DB oncreate <>---<>---<>");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS_DECK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARCHETYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);

        onCreate(db);
    }
    public int getNbRows(){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT ID FROM " + TABLE_DECKS;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int nbRow = c.getCount();
        db.close();
        return nbRow;
    }
    public int getId(int pos){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT ID FROM " + TABLE_DECKS;
        Cursor c = db.rawQuery(query, null);
        c.moveToPosition(pos);
        int deckId = c.getInt(0);
        db.close();
        return deckId;
    }
    public String getDeckName(int deckId){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Name FROM " + TABLE_DECKS + " WHERE ID = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String deckName = c.getString(0);
        db.close();
        return deckName;
    }
    public int getFormat(int deckId){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Format_ID FROM " + TABLE_DECKS + " WHERE ID = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int formatName = c.getInt(0);
        db.close();
        return formatName;
    }
    public String getImageName(String deckName){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Colors_Name FROM " + TABLE_DECKS + " WHERE " + KEY_NAME + " = '" + deckName + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String deckColor = c.getString(0);
        db.close();
        return deckColor;
    }
}
