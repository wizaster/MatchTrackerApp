package model;

public class DeckId {
    private int id;
    private String name;

    public DeckId(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
