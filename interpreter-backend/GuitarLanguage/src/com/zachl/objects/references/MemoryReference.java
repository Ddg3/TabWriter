package com.zachl.objects.references;

import com.zachl.objects.data.ChordStructure;
import com.zachl.registries.MemoryRegistry;

import java.sql.Ref;

public class MemoryReference extends Reference {
    protected static final ChordStructure structure = new ChordStructure(new int[]{-1,-1,-1,-1,0,-2});
    public MemoryReference(int index){
        super(index, structure);
    }
}
