package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Argument;

import java.util.Queue;

public class InstructionFlag extends Flag{
    private static final ChordStructure localArgClose = new ChordStructure(new int[]{-2, 0, 0});
    public InstructionFlag() {
        super("i", new ChordStructure(new int[]{-1, 0, 0}), localArgClose, new FlagInterpreter() {
            @Override
            public Argument interpret(Queue<Chord> chords) {
                Argument arg = new Argument("i", new String[]{});
                if(chords.size() > 0) {
                    Chord c = chords.poll();
                    int index = c.getFrets()[0];
                    if(localArgClose.matches(c)) {
                        arg = arg.addParameter("save to " + index);
                    }
                    else{
                        arg = arg.addParameter("run from " + index);
                    }
                }
                return arg;
            }
        });
    }
}
