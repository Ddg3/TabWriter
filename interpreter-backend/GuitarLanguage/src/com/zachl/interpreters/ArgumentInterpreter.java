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
            outerLoop:
            for(Flag f : Flag.allFlags()){
                if(!flagStack.empty()){
                    if(f.name.equalsIgnoreCase(flagStack.peek().name) && flagStack.peek().open.matches(c)){
                        flagStack.pop();
                        args.add(f.interpret(argChords));
                        argChords.clear();
                    }
                    else if(f.name.equalsIgnoreCase(flagStack.peek().name) && flagStack.peek().argClose.matches(c)){
                        flagStack.pop();
                        argChords.add(c);
                        args.add(f.interpret(argChords));
                        argChords.clear();
                    }
                    else if(f.open.matches(c)){
                        flagStack.push(f);
                    }
                    else{
                        for(Flag f2 : Flag.allFlags()){
                            if(f2.open.matches(c) || f2.argClose.matches(c))
                                continue outerLoop;
                        }
                        argChords.add(c);
                    }
                    break;
                }
                else if (f.open.matches(c)){
                    flagStack.push(f);
                    if(!args.isEmpty()){
                        final Queue<Argument> tempArgs = args;
                        instructions.add(new Instruction(tempArgs));
                        args.clear();
                    }
                    break;
                }
            }
        }
        if(!args.isEmpty())
            instructions.add(new Instruction(args));
    }

}
