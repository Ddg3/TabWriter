package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Argument;
import com.zachl.objects.execution.Error;
import com.zachl.objects.execution.InstructionHead;
import com.zachl.objects.references.MemoryReference;
import com.zachl.objects.references.Reference;

import java.util.Queue;

public class ArithmeticFlag extends Flag{
    public ArithmeticFlag() {
        super("a", new ChordStructure(new int[]{0, -1, 0}), new ChordStructure(new int[]{0, -2, 0}), new FlagInterpreter() {
            @Override
            public Argument interpret(Queue<Chord> chords) {
                int size = chords.size();
                if (!(size >= 1 && size <= 4)) {
                    InstructionHead.throwError(new Error("Arithmetic Error"));
                    return null;
                }
                String num1, num2 = "0";
                int lastRoot;
                int refId = -1;
                Operator op = Operator.Plus;

                Chord c = chords.poll();
                num1 = evaluateNumber(c);
                lastRoot = c.getRoot().getFret();
                if(size == 2){
                    refId = evaluateParam(chords.poll());
                }
                else if(size == 3){
                    op = evaluateOperator(chords.poll(), lastRoot);
                    num2 = evaluateNumber(chords.poll());
                }
                else if(size == 4){
                    refId = evaluateParam(chords.poll());
                }
                Argument arg = new Argument("a", new String[]{num1 + " " + op.symbol + " " + num2});
                if(refId != -1){
                    arg = arg.addParameter("" + refId);
                }
                return arg;
            }
        });
    }

    private enum Operator{
        Plus("+"),
        Minus("-"),
        Multiply("*"),
        Divide("/");
        String symbol;
        Operator(String symbol){
            this.symbol = symbol;
        }
    }
    private static int evaluateParam(Chord c){
        return c.getFrets()[1];
    }
    private static Operator evaluateOperator(Chord c, int lastRoot){
        int interval = lastRoot - c.getRoot().getFret();
        if(interval < 0 || interval > 3){
            InstructionHead.throwError(new Error("Arithmetic Operator Error"));
            return null;
        }
        return Operator.values()[interval];
    }
    protected static String evaluateNumber(Chord c){
        MemoryReference ref = Reference.getMemoryReference(c);
        if(ref != null)
            return ref.toString();
        int value = 0;
        int valid = c.lastValidFret();
        for (int i = 0; i < valid; i++) {
            if(c.getFrets()[i] == -1)
                continue;
            if (i < 3) {
                value *= 10;
                value += c.getFrets()[i];
            } else if (i == 3) {
                value += c.getFrets()[i];
            } else {
                value += c.getFrets()[i] - 1;
            }
        }
        return "" + value;
    }
}
