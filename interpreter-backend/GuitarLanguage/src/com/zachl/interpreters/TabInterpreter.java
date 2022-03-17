package com.zachl.interpreters;

import com.zachl.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TabInterpreter {
    private Queue<int[]> notes;
    private Map<Integer, char[]> dynamics;
    public TabInterpreter(File file) throws IOException {
        int STRINGS = Constants.STRINGS;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        notes = new LinkedList<>();
        dynamics = new HashMap<>();
        String[] lines = new String[STRINGS];
        for(int i = 0; i < STRINGS; i++){
            lines[i] = reader.readLine();
        }
        for(int i = 0; i < lines[0].length() - 1; i++){
            int[] frets = new int[STRINGS];
            char[] subDynamics = new char[STRINGS];
            for(int j = 0; j < lines.length; j++){
                int index = lines.length - j - 1;
                char last = lines[j].charAt(Math.max(0, i - 1));
                char c = lines[j].charAt(i);
                char next = lines[j].charAt(i + 1);
                if(!(i != 0 && toFret(last) != -1 && toFret(c) != -1))
                    frets[index] += toFret(c);
                else
                    frets[index] = -1;
                if(frets[index] != -1 && toFret(next) != -1){
                    frets[index] *= 10;
                    frets[index] += toFret(next);
                }
                subDynamics[index] = c;
            }
            if(!barIsEmpty(frets))
                notes.add(frets);
            if(!barIsEmpty(subDynamics))
                dynamics.put(i, subDynamics);
        }
    }
    public Queue<int[]> getNotes(){
        return notes;
    }
    private boolean barIsEmpty(int[] frets){
        for(int n : frets){
            if(n != -1)
                return false;
        }
        return true;
    }
    private boolean barIsEmpty(char[] dynamics){
        for(char c : dynamics){
            if(c != '-' && toFret(c) == -1)
                return false;
        }
        return true;
    }
    private int toFret(char c){
        if(c > 47 && c < 58){
            return Integer.parseInt("" + c);
        }
        return -1;
    }
}
