package com.zachl.registries;

import java.util.HashMap;

public class MemoryRegistry {
    private static HashMap<Integer, Integer> memoryRegistry = new HashMap<>();
    private static int maxIndex = 0;
    public static void appendValue(int value){
        memoryRegistry.put(maxIndex, value);
        maxIndex++;
    }
    public static void replaceValue(int index, int value){
        if(index > maxIndex)
            index = maxIndex;
        if(index == maxIndex)
            maxIndex++;
        memoryRegistry.put(index, value);
    }
    public static int getValueAt(int index){
        return memoryRegistry.get(index);
    }
    public static int getMostRecent(){
        return memoryRegistry.get(memoryRegistry.size() - 1);
    }
}
