package com.zachl.objects.references;

import com.zachl.objects.data.Chord;
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
    public static MemoryReference getMemoryReference(Chord c) {
        if (MemoryReference.structure.matches(c)) {
            return new MemoryReference(c.getFrets()[5]);
        }
        return null;
    }
    public static MemoryReference getInstructionReference(Chord c) {
        if (InstructionReference.structure.matches(c)) {
            return new MemoryReference(c.getFrets()[4]);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ref(" + index + ")";
    }
}
