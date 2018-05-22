package com.example.okovacs.dictionary2;

/**
 * Created by okovacs on 4/21/2018.
 */

public class Word {
    private String name;
    private String description;

    public Word(String name, String description){
        this.name=name;
        this.description=description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
