package com.zachl.objects.flags;

import com.zachl.objects.data.Chord;
import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Argument;
import com.zachl.objects.execution.Error;
import com.zachl.objects.execution.InstructionHead;

import java.util.Queue;

public class ArithmeticFlag extends Flag{
    public ArithmeticFlag() {
        super(new ChordStructure(new int[]{0, -1, 0}), new ChordStructure(new int[]{0, -2, 0}), new FlagInterpreter() {
            @Override
            public Argument interpret(Queue<Chord> chords) {
                if (chords.size() != 1 && chords.size() != 3) {
                    InstructionHead.throwError(new Error("Arithmetic Error"));
                    return null;
                }
                int num1 = 0, num2 = 0, lastRoot = 0;
                Operator op = Operator.Plus;
                for(int i = 0; i < chords.size(); i++){
                    if(i == 0) {
                        Chord c = chords.poll();
                        num1 = evaluateNumber(c);
                        lastRoot = c.getRoot().getFret();
                    }
                    if(i == 1)
                        op = evaluateOperator(chords.poll(), lastRoot);
                    else{
                        num2 = evaluateNumber(chords.poll());
                    }
                }
                int result = num1;
                switch(op){
                    case Plus:
                        result += num2;
                        break;
                    case Minus:
                        result -= num2;
                        break;
                    case Multiply:
                        result *= num2;
                        break;
                    case Divide:
                        result /= num2;
                        break;
                }
                return new Argument("a", new String[]{"" + result});
            }
        });
    }

    private enum Operator{
        Plus,
        Minus,
        Multiply,
        Divide;
    }
    private static Operator evaluateOperator(Chord c, int lastRoot){
        int interval = lastRoot - c.getRoot().getFret();
        if(interval < 0 || interval > 3){
            InstructionHead.throwError(new Error("Arithmetic Operator Error"));
            return null;
        }
        return Operator.values()[interval];
    }
    private static int evaluateNumber(Chord c){
        int value = 0;
        int valid = c.getFrets().length;
        for (int i = 0; i < valid; i++) {
            if (i < 3) {
                value *= 10;
                value += c.getFrets()[i];
            } else if (i == 3) {
                value += c.getFrets()[i];
            } else {
                value += c.getFrets()[i] - 1;
            }
        }
        return value;
    }
}
