package com.zachl.objects.flags;

import com.zachl.objects.execution.Argument;
import com.zachl.objects.execution.Error;
import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;

import java.util.Queue;

public class Flag {
    public ChordStructure open, argClose;
    public String name;
    private FlagInterpreter interpreter;
    public Flag(String name, ChordStructure open, ChordStructure argClose, FlagInterpreter interpreter){
        this.name = name;
        this.open = open;
        this.argClose = argClose;
        this.interpreter = interpreter;
    }
    public Argument interpret(Queue<Chord> chords){
        return interpreter.interpret(chords);
    }

    public static Flag[] allFlags(){
        return new Flag[]{new ArithmeticFlag(), new PrintFlag(), new InstructionFlag(), new GoToFlag()};
    }
}
