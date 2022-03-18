package com.zachl.objects.execution;

import com.zachl.objects.references.InstructionReference;
import com.zachl.registries.InstructionRegistry;
import com.zachl.registries.MemoryRegistry;

import java.util.Queue;

public class Instruction {
    private static final int NAN_VALUE = -3;
    public int position;
    public Queue<Argument> args;
    public Instruction(Queue<Argument> args){
       //this.position = position;
       this.args = args;
    }
    public void execute(InstructionHead head) {
        int arithValue = NAN_VALUE;
        for(Argument arg : args){
            switch (arg.arg){
                case "a":
                    arithValue = Integer.parseInt(arg.parameters.get(0));
                    break;
                case "r":
                    if(arg.parameters.size() == 0)
                        MemoryRegistry.appendValue(arithValue);
                    else
                        MemoryRegistry.replaceValue(Integer.parseInt(arg.parameters.get(0)), arithValue);
                    break;
                case "i":
                    if(arg.parameters.size() == 0)
                        InstructionRegistry.appendInstruction(this);
                    else
                        InstructionRegistry.replaceInstruction(Integer.parseInt(arg.parameters.get(0)), this);
                    break;
                case "p":
                    if(arg.parameters.size() == 0)
                        System.out.println(arithValue);
                    else
                        System.out.println((char)arithValue);
                    break;
            }
        }
    }
}
