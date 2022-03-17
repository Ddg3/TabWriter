package com.zachl.objects.execution;

import com.zachl.objects.data.Chord;

public class Argument {
    String[] parameters;
    String arg;
    public Argument(String arg, String[] parameters){
        this.arg = arg;
        this.parameters = parameters;
    }
    public Argument addParameter(Chord param){
        return this;
    }
}
