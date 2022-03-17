package com.zachl.registries;

import com.zachl.objects.execution.Instruction;

import java.util.HashMap;

public class InstructionRegistry {
    private static HashMap<Integer, Instruction> instructionRegistry = new HashMap<>();
    private static int maxIndex = 0;
    public static void appendInstruction(Instruction i){
        instructionRegistry.put(maxIndex, i);
    }
    public static void replaceInstruction(int index, Instruction i){
        if(index > maxIndex)
            index = maxIndex;
        if(index == maxIndex)
            maxIndex++;
        instructionRegistry.put(index, i);
    }
    public static Instruction getInstructionAt(int index){
        return instructionRegistry.get(index);
    }
    public static Instruction getMostRecent(){
        return instructionRegistry.get(instructionRegistry.size() - 1);
    }
}
