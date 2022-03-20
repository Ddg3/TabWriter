package com.zachl.objects.execution;

import com.zachl.objects.references.InstructionReference;
import com.zachl.objects.references.MemoryReference;
import com.zachl.registries.InstructionRegistry;
import com.zachl.registries.MemoryRegistry;

import java.util.LinkedList;
import java.util.Queue;

public class Instruction {
    private static final int NAN_VALUE = -3;
    public int position;
    public Queue<Argument> args = new LinkedList<>();
    public Instruction(Queue<Argument> args){
       for(Argument a : args){
           this.args.add(a);
       }

    }
    private static int parseInteger(String s){
        if(s.contains("ref")){
            return MemoryRegistry.getValueAt(Integer.parseInt("" + s.charAt(4)));
        }
        else{
            return Integer.parseInt(s);
        }
    }
    public void execute(InstructionHead head) {
        int arithValue = NAN_VALUE;
        for(Argument arg : args){
            switch (arg.arg){
                case "a":
                    String[] arith = arg.parameters.get(0).split(" ");
                    arithValue = parseInteger(arith[0]);
                    int num = parseInteger(arith[2]);
                    switch (arith[1]){
                        case "+":
                            arithValue += num;
                            break;
                        case "-":
                            arithValue -= num;
                            break;
                        case "*":
                            arithValue *= num;
                            break;
                        case "/":
                            arithValue /= num;
                    }
                    if(arg.parameters.size() == 2)
                        MemoryRegistry.replaceValue(Integer.parseInt(arg.parameters.get(1)), arithValue);
                    break;
                case "i":
                    if(arg.parameters.size() == 0)
                        InstructionRegistry.appendInstruction(this);
                    else {
                        if(arg.parameters.get(0).contains("save")) {
                            InstructionRegistry.replaceInstruction(Integer.parseInt(arg.parameters.get(0).split("to ")[1]), this);
                        }
                        else{
                            Instruction instruction = InstructionRegistry.getInstructionAt(Integer.parseInt(arg.parameters.get(0).split("from ")[1]));
                            instruction.execute(head);
                        }
                    }
                    break;
                case "p":
                    if(arg.parameters.size() == 0)
                        System.out.println(arithValue);
                    else
                        System.out.println((char)arithValue);
                    break;
                case "g":
                    if(arg.parameters.size() == 1) {
                        head.goToPosition(Integer.parseInt(arg.parameters.get(0)));
                        arg.parameters.add("-1");
                    }
                    else{
                        int count = Integer.parseInt(arg.parameters.get(1)) - 1;
                        if(count >= 0) {
                            head.goToPosition(Integer.parseInt(arg.parameters.get(0)));
                            arg.parameters.set(1, "" + count);
                        }
                    }
            }
        }
    }
}
