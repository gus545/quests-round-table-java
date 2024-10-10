package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Deck<T> {

    protected List<T> cards;

    public Deck(){
        cards = new ArrayList<>();
    }



}
