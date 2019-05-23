package Database;

import android.content.Context;
import android.database.Cursor;
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
    private static final String TAG = "DatabaseHelper";
    //database version
    public static int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "MatchTracker";
    //Table names
    private static final String TABLE_PLAYERS = "Players_List";
    private static final String TABLE_DECKS = "Decks_Name";
    private static final String TABLE_ARCHETYPE = "Archetypes";
    private static final String TABLE_COLORS = "Colors";
    private static final String TABLE_FORMAT = "Format";
    private static final String TABLE_GAME_DETAILS = "Game_Details";
    private static final String TABLE_MATCH = "Matches";
    private static final String TABLE_USERS_DECK = "Users_Deck";
    //Common column name
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "Name";
    //Users_Deck table - coloummn names
    private static final String KEY_FORMAT_ID = "Format_ID";
    private static final String KEY_ARCHETYPE_ID = "Archetype_ID";
    private static final String KEY_COLORS_NAME = "Colors_Name";
    private static final String KEY_WIN_RATE = "Win_Rate";
    private static final String KEY_PLAYERS_LIST_ID = "Players_List_ID";
    //Game_Details - column names
    private static final String KEY_MATCH_ID = "match_ID";
    private static final String KEY_GAME_NO = "Game_NO";
    private static final String KEY_ON_THE_PLAY = "On_The_Play";
    private static final String KEY_WIN = "Win";
    //Matches table - column names
    private static final String KEY_ARCHETYPE_1_ID = "Archetype_1_ID";
    private static final String KEY_ARCHETYPE_2_ID = "Archetype_2_ID";
    private static final String KEY_SCORE_1 = "Score_1";
    private static final String KEY_SCORE_2 = "Score_2";
    private static final String KEY_DRAW = "Draw";
    private static final String KEY_TOTAL_GAME = "Total_Game";
    private static final String KEY_WINNER = "Winner";
    //COLORS TABLE - COLUMN NAME
    private static final String KEY_REFERENCE_IMAGE = "Reference_Image";
    //table create statement
    //TABLE_PLAYERS
    private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYERS
            +"(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, " + KEY_NAME  + " VARCHAR2(50))";
    //TABLE_DECKS
    private static final String CREATE_TABLE_DECKS = "CREATE TABLE IF NOT EXISTS " + TABLE_DECKS
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
    private static final String CREATE_TABLE_ARCHETYPE = "CREATE TABLE IF NOT EXISTS " +TABLE_ARCHETYPE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45))";
    //TABLE_COLORS
    private static final String CREATE_TABLE_COLORS = "CREATE TABLE IF NOT EXISTS " +TABLE_COLORS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45), "
            + KEY_REFERENCE_IMAGE + " VARCHAR2(45))";
    //TABLE_FORMAT
    private static final String CREATE_TABLE_FORMAT = "CREATE TABLE IF NOT EXISTS " +TABLE_FORMAT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL, "
            + KEY_NAME + " VARCHAR(45))";
    //TABLE_GAME_DETAILS
    private static final String CREATE_TABLE_GAME_DETAILS = "CREATE TABLE IF NOT EXISTS " + TABLE_GAME_DETAILS
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
    private static final String CREATE_TABLE_MATCH = "CREATE TABLE IF NOT EXISTS " + TABLE_MATCH
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
    private static final String CREATE_TABLE_USERS_DECK = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS_DECK
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
        c.close();
        db.close();
        return nbRow;
    }
    public int getId(int pos){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT ID FROM " + TABLE_DECKS;
        Cursor c = db.rawQuery(query, null);
        c.moveToPosition(pos);
        int deckId = c.getInt(0);
        c.close();
        db.close();
        return deckId;
    }
    public String getDeckName(int deckId){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Name FROM " + TABLE_DECKS + " WHERE ID = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String deckName = c.getString(0);
        c.close();
        db.close();
        return deckName;
    }
    public int getFormat(int deckId){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Format_ID FROM " + TABLE_DECKS + " WHERE ID = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int formatInt = c.getInt(0);
        c.close();
        db.close();
        return formatInt;
    }
    public String getFormatName(int deckId){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Format_ID FROM " + TABLE_DECKS + " WHERE ID = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int formatInt = c.getInt(0);
        query = "SELECT Name FROM " + TABLE_FORMAT + " WHERE ID = '" + formatInt + "'";
        c = db.rawQuery(query, null);
        c.moveToFirst();
        String formatName = c.getString(0);
        c.close();
        db.close();
        return formatName;
    }
    public String getImageName(String deckName){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT Colors_Name FROM " + TABLE_DECKS + " WHERE " + KEY_NAME + " = '" + deckName + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String deckColor = c.getString(0);
        c.close();
        db.close();
        return deckColor;
    }
    public double getWinRate(int deckId){

        SQLiteDatabase db =  this.getWritableDatabase();
        double win = 0;
        double loss = 0;
        double winRate = 0;
        String query = "SELECT * FROM " + TABLE_MATCH + " WHERE " + KEY_ARCHETYPE_1_ID + " = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() > 0){
            while(!c.isAfterLast()){
                win += c.getInt(3);
                loss += c.getInt(4);
                c.moveToNext();
            }
            winRate = ((win*100) / (win + loss));
        }
        c.close();
        db.close();
        return winRate;
    }
    public int getNbGames(int deckId){
        SQLiteDatabase db =  this.getWritableDatabase();
        int total = 0;
        String query = "SELECT * FROM " + TABLE_MATCH + " WHERE " + KEY_ARCHETYPE_1_ID + " = '" + deckId + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            total += c.getInt(3) + c.getInt(4);
            c.moveToNext();
        }
        c.close();
        db.close();
        return total;
    }
}
