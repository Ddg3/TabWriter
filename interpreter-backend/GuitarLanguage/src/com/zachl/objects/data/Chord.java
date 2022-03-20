package com.zachl.objects.data;

import com.zachl.Constants;

import java.util.*;
import java.util.Map.Entry;

public class Chord {
    protected Note root;
    protected Set<Integer> intervals;
    protected boolean hasOctave = false;
    protected int[] frets;
    protected Note[] notes = new Note[6];
    private String name = "";
    private Chord(Note root, Set<Integer> intervalSet, int[] frets){
        this.root = root;
        this.intervals = intervalSet;
        this.frets = frets;
        for(int i = 0; i < notes.length; i++){
            if(frets[i] == -1){
                notes[i] = null;
                continue;
            }
            notes[i] = new Note(frets[i], i);
        }
        intervals.removeIf(o -> o == 0);
        if(intervals.size() != intervalSet.size())
            hasOctave = true;
        int[] tempIntervals = new int[intervals.size()];
        int i = 0;
        for(int interval : intervals){
            tempIntervals[i] = interval;
            i++;
        }
        if(tempIntervals.length > 0) {
            outerLoop:
            for (Entry<String, int[]> entry : Constants.CHORDS.entrySet()) {
                for (i = 0; i < entry.getValue().length; i++) {
                    if (tempIntervals[i] != entry.getValue()[i])
                        continue outerLoop;
                }
                name = entry.getKey();
            }
            if (name.equalsIgnoreCase("")) {
                if (hasOctave)
                    name = "Octave";
                else
                    name = Arrays.toString(tempIntervals);
            }
        }
    }

    public static Chord getChord(int[] notes){
        Note root = null;
        Queue<Note> intervalNotes = new LinkedList<>();
        for(int i = 0; i < notes.length; i++){
            if(notes[i] != -1) {
                if (root == null) {
                    root = new Note(notes[i], i);
                    continue;
                }
                intervalNotes.add(new Note(notes[i], i));
            }
        }
        Set<Integer> intervals = new TreeSet<>();
        for(Note n : intervalNotes){
            intervals.add(root.intervalTo(n));
        }
        return new Chord(root, intervals, notes);
    }
    public int[] getFrets(){
        return frets;
    }
    public int lastValidFret(){
        int max = 0;
        for(int i = 0; i < frets.length; i++){
            if(frets[i] != -1)
                max = i + 1;
        }
        return max;
    }
    public Note getRoot(){
        return root;
    }
    public Note noteAtString(int stringIndex){
        return notes[stringIndex];
    }
    @Override
    public String toString(){
        return root.notation + name;
    }
}
