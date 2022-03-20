package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Argument;
import com.zachl.registries.MemoryRegistry;

import java.util.Queue;

public class GoToFlag extends Flag{
    public GoToFlag() {
        super("g", new ChordStructure(new int[]{-1, 0, -1, 0}), new ChordStructure(new int[]{-1, 0, -2, 0}), new FlagInterpreter() {
            @Override
            public Argument interpret(Queue<Chord> chords) {
                String index = ArithmeticFlag.evaluateNumber(chords.poll());
                Argument arg = new Argument("g", new String[]{"" + index});
                if(!chords.isEmpty()){
                    int count = chords.poll().getFrets()[2];
                    arg.addParameter("" + count);
                }
                return arg;
            }
        });
    }
}
