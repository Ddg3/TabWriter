package com.zachl.interpreters;

import com.zachl.objects.execution.Argument;
import com.zachl.objects.data.Chord;
import com.zachl.objects.flags.Flag;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

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
    public Queue<Argument> interpretArguments(){
        Queue<Argument> args = new LinkedList<>();
        boolean openCaught = false;
        Queue<Chord> argChords = new LinkedList<>();
        for(Chord c : chords){
            for(Flag f : Flag.allFlags()){
                if(!openCaught) {
                    if (f.open.matches(c)){
                        openCaught = true;
                        break;
                    }
                }
                else{
                    if(f.open.matches(c)){
                        openCaught = false;
                        args.add(f.interpret(argChords));
                        argChords.clear();
                        break;
                    }
                    if(f.argClose.matches(c)){
                        openCaught = false;
                        Chord param = f.argClose.getParameter(c);
                        args.add(f.interpret(argChords).addParameter(param));
                        argChords.clear();
                        break;
                    }
                }
            }
            argChords.add(c);
        }
        return args;
    }

}
