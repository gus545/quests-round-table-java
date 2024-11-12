package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T> {

    protected List<T> cards;
    protected int deckSize;

    public Deck(){
        cards = new ArrayList<>();
        deckSize = 0;
    }

    public void addCard(T card){
        cards.add(card);
        deckSize++;
    }

    public void addCards(List<T> cards){
        this.cards.addAll(cards);
        deckSize += cards.size();
    }

    public T drawCard(){

        T card = cards.removeFirst();
        deckSize--;
        return card;
    }
    public int getSize(){
        return deckSize;
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    public void setCard(int i, T card){
        if(cards.isEmpty()) addCard(card);
        else cards.set(i,card);
    }

    public void rigDeck(List<T> newCards){
        for(T c : newCards){
            cards.remove(c);
        }
        cards.addAll(0,newCards);
    }


}
