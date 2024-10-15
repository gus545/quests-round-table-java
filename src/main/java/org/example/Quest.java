package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

}
