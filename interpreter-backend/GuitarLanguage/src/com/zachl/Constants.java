package com.zachl;

import java.util.HashMap;

public class Constants {
    public static final HashMap<Integer, String> AVAILABLE_NOTES = new HashMap<>();
    public static final HashMap<Integer, int[]> STRING_VALUES = new HashMap<>();
    public static final HashMap<String, int[]> CHORDS = new HashMap<>();
    public static final int STRINGS = 6;

    public static void init(){
        AVAILABLE_NOTES.put(0, "E");
        AVAILABLE_NOTES.put(1, "F");
        AVAILABLE_NOTES.put(2, "F#");
        AVAILABLE_NOTES.put(3, "G");
        AVAILABLE_NOTES.put(4, "G#");
        AVAILABLE_NOTES.put(5, "A");
        AVAILABLE_NOTES.put(6, "A#");
        AVAILABLE_NOTES.put(7, "B");
        AVAILABLE_NOTES.put(8, "C");
        AVAILABLE_NOTES.put(9, "C#");
        AVAILABLE_NOTES.put(10, "D");
        AVAILABLE_NOTES.put(11, "D#");
        AVAILABLE_NOTES.put(12, "E");

        //int[0] = note value on open
        //int[1] = octave of first E
        STRING_VALUES.put(0, new int[]{0,0});
        STRING_VALUES.put(1, new int[]{5, 1});
        STRING_VALUES.put(2, new int[]{10, 1});
        STRING_VALUES.put(3, new int[]{3, 2});
        STRING_VALUES.put(4, new int[]{7, 2});
        STRING_VALUES.put(5, new int[]{0, 2});

        CHORDS.put("Maj", new int[]{4, 7});
        CHORDS.put("Min", new int[]{3, 7});
        CHORDS.put("Dim", new int[]{3, 6});
        CHORDS.put("Aug", new int[]{4, 8});
        CHORDS.put("Power", new int[]{7});
        CHORDS.put("Octave", new int[]{0});
        CHORDS.put("Double", new int[]{5});
    }
}
