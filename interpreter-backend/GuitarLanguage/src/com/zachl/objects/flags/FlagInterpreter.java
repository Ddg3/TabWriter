package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.execution.Argument;

import java.util.Queue;

public interface FlagInterpreter {
    Argument interpret(Queue<Chord> chords);
}
