package org.example;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Quest {

    private ArrayList<Stage> stages;
    private int numStages;
    private int max;

    public Quest(int numStages){
        stages = new ArrayList<>();
        this.numStages = numStages;
        this.max = 0;
    }

    public ArrayList<Stage> getStages(){ return stages;}

    public void addStage(Stage s){
        stages.add(s);
        max = s.getValue();
    }

    public int getMax(){ return max;}

    public TreeSet<Player> getParticipants(){ return new TreeSet<>();}

    public TreeSet<Player> getIneligible(){return new TreeSet<>();}

    public void removeParticipants(Set<Player> s){
    }

    public void displayParticipants(PrintWriter pw){

    }
}
