package com.zachl.objects.execution;

import java.util.LinkedList;

public class InstructionQueue {
    private int size = 0;
    private InstructionNode root;
    private class InstructionNode{
        Instruction instruction;
        InstructionNode next;
        public InstructionNode(Instruction instruction){
            this.instruction = instruction;
        }
    }
    public int size(){
        return size;
    }
    public void append(Instruction instruction){
        if(root == null) {
            root = new InstructionNode(instruction);
            size++;
            return;
        }
        InstructionNode temp = root;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new InstructionNode(instruction);
        size++;
    }
    public void insertAt(Instruction instruction, int index){
        if(root == null) {
            root = new InstructionNode(instruction);
            size++;
            return;
        }
        InstructionNode temp = root;
        int i = 0;
        while(temp.next != null && i < index){
            temp = temp.next;
            i++;
        }
        InstructionNode temp2 = temp.next;
        temp.next = new InstructionNode(instruction);
        temp.next.next = temp2;
        size++;
    }
    public Instruction firstInstruction(){
        return root.instruction;
    }
    public Instruction lastInstruction(){
        InstructionNode temp = root;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp.instruction;
    }
    public Instruction instructionAt(int index){
        InstructionNode temp = root;
        int i = 0;
        while(temp.next != null && i < index){
            temp = temp.next;
            i++;
        }
        return temp.instruction;
    }
}
