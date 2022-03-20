package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Argument;

import java.util.Queue;

public class PrintFlag extends Flag{
    public PrintFlag() {
        super("p", new ChordStructure(new int[]{0, 0, -1}), new ChordStructure(new int[]{0, 0, -2}), new FlagInterpreter() {
            @Override
            public Argument interpret(Queue<Chord> chords) {
                if(chords.isEmpty()){
                    return new Argument("p", new String[]{});
                }
                else{
                    return new Argument("p", new String[]{"s"});
                }
            }
        });
    }
}
