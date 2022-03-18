package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Argument;

import java.util.Queue;

public class InstructionFlag extends Flag{
    public InstructionFlag(ChordStructure open, ChordStructure argClose, FlagInterpreter interpreter) {
        super(new ChordStructure(new int[]{-1, 0, 0}), new ChordStructure(new int[]{-1, 0, 0, 0}), new FlagInterpreter() {
            @Override
            public Argument interpret(Queue<Chord> chords) {
                return null;
            }
        });
    }
}
