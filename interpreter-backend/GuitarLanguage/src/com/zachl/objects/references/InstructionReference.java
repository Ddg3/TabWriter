package com.zachl.objects.references;

import com.zachl.objects.data.ChordStructure;
import com.zachl.objects.execution.Instruction;
import com.zachl.registries.InstructionRegistry;

import java.sql.Ref;

public class InstructionReference extends Reference {
    protected static final ChordStructure structure = new ChordStructure(new int[]{-1,-1,-1,-1,-2,0});
    public InstructionReference(int index){
        super(index, structure);
    }
}
