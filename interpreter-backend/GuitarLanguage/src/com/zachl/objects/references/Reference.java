package com.zachl.objects.references;

import com.zachl.objects.data.ChordStructure;

public class Reference {
    protected int index;
    private ChordStructure structure;
    public Reference(int index, ChordStructure structure){
        this.index = index;
        this.structure = structure;
    }
    public static ChordStructure[] allReferenceStructures(){
        return new ChordStructure[]{MemoryReference.structure, InstructionReference.structure};
    }
}
