package com.zachl.objects.execution;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Argument {
    ArrayList<String> parameters = new ArrayList<>();
    String arg;
    public Argument(String arg, String[] params){
        this.arg = arg;
        parameters.addAll(Arrays.asList(params));
    }
    public Argument addParameter(Note param){
        parameters.add("id at " + param.getFret());
        return this;
    }
}
