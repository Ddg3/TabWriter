package com.zachl.interpreters;

import com.zachl.objects.execution.Argument;
import com.zachl.objects.data.Chord;
import com.zachl.objects.execution.Instruction;
import com.zachl.objects.flags.Flag;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArgumentInterpreter {
    private Queue<Chord> chords;
    public ArgumentInterpreter(File file){
        try {
            TabInterpreter tabInterpreter = new TabInterpreter(file);
            chords = getChords(tabInterpreter.getNotes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    protected Queue<Chord> getChords(Queue<int[]> tab){
        Queue<Chord> res = new LinkedList<>();
        for(int[] arr : tab){
            res.add(Chord.getChord(arr));
        }
        return res;
    }
    public void interpretArguments(Queue<Instruction> instructions){
        Queue<Argument> args = new LinkedList<>();
        Queue<Chord> argChords = new LinkedList<>();
        Stack<Flag> flagStack = new Stack<>();
        for(Chord c : chords){
            for(Flag f : Flag.allFlags()){
                if(!flagStack.empty()){
                    if(flagStack.peek().open.matches(c)){
                        flagStack.pop();
                        args.add(f.interpret(argChords));
                        argChords.clear();
                        break;
                    }
                    if(flagStack.peek().argClose.matches(c)){
                        flagStack.pop();
                        argChords.add(c);
                        args.add(f.interpret(argChords));
                        argChords.clear();
                        break;
                    }
                    if(f.open.matches(c)){
                        flagStack.push(f);
                    }
                    else{
                        argChords.add(c);
                    }
                }
                else if (f.open.matches(c)){
                    flagStack.push(f);
                    if(!args.isEmpty()){
                        instructions.add(new Instruction(args));
                        args.clear();
                    }
                    break;
                }
            }
        }
    }

}
