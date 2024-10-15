package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Quest {

    private ArrayList<Stage> stages;

    public Quest(){
        stages = new ArrayList<>();
    }

    public ArrayList<Stage> getStages(){ return stages;}

}
