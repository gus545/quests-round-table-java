package org.example;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class Quest {

    private final ArrayList<Stage> stages;
    private TreeMap<Player,Boolean> eligible;
    private final int numStages;
    private int max;

    public Quest(int numStages, List<Player> players, Player sponsor){

        eligible = new TreeMap<>();
        for(Player p : players) eligible.put(p,true);
        eligible.put(sponsor,false);

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

    public TreeSet<Player> getParticipants(){
        TreeSet<Player> out = new TreeSet<>();
        for (Player p : eligible.keySet()){
            if(eligible.get(p)){
                out.add(p);
            }
        }
        return out;
    }

    public TreeSet<Player> getIneligible(){
        TreeSet<Player> out = new TreeSet<>();
        for (Player p : eligible.keySet()){
            if(!eligible.get(p)){
                out.add(p);
            }
        }
        return out;
    }

    public void removeParticipants(Set<Player> s){
        for(Player p: s) eligible.put(p,false);
    }

    public void removeParticipants(Player p){
        eligible.put(p,false);
    }

    public void displayParticipants(PrintWriter pw){
        StringBuilder out = new StringBuilder("Remaining participants:");
        for(Player p : getParticipants()) out.append(" ").append(p);
        pw.println(out);
    }

    public List<Integer> getIneligibleIds() {
        ArrayList<Integer> out = new ArrayList<>();
        for(Player p : getIneligible()) out.add(p.getId()-1);
        return out;
    }

}
