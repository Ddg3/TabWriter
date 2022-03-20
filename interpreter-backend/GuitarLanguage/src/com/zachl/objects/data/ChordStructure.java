package com.zachl.objects.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ChordStructure {
    public HashMap<Integer, Integer> requiredFrets = new HashMap<>(), paramFrets = new HashMap<>();
    public ChordStructure(int[] frets){
        for(int i = 0; i < frets.length; i++){
            if(frets[i] == -2) {
                paramFrets.put(i, frets[i]);
                continue;
            }
            requiredFrets.put(i, frets[i]);
        }
    }
    public boolean matches(Chord c){
        for(Map.Entry<Integer, Integer> e: requiredFrets.entrySet()){
            if(e.getValue() != c.getFrets()[e.getKey()]){
                return false;
            }
        }
        return true;
    }

    public Chord getParameter(Chord c){
        int[] notes = new int[paramFrets.size()];
        int i = 0;
        for(Map.Entry<Integer, Integer> e : paramFrets.entrySet()){
            notes[i] = c.noteAtString(e.getKey()).fret;
            i++;
        }
        return Chord.getChord(notes);
    }
}
