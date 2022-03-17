package com.zachl.objects.data;

import com.zachl.Constants;

public class Note {
    protected String notation;
    protected int value, octave, fret;
    public Note(String notation, int value, int octave, int fret){
        this.notation = notation;
        this.value = value;
        this.octave = octave;
        this.fret = fret;
    }
    public Note(int note, int string){
        this(Constants.AVAILABLE_NOTES.get((Constants.STRING_VALUES.get(string)[0] + note) % 12),
                (Constants.STRING_VALUES.get(string)[0] + note) % 12,
                Constants.STRING_VALUES.get(string)[1] + (note / 12),
                note);
    }
    public int getFret(){
        return fret;
    }
    public int intervalTo(Note other){
        return Math.abs(value - other.value);
    }
    @Override
    public String toString(){
        return notation + (octave + 1);
    }
}
