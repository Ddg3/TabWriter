package com.zachl.objects.execution;

import java.util.*;

public class InstructionHead {
    private static boolean debug;
    private static Scanner debugScanner;
    private static int exitCode;

    private static int headPosition = 0;
    private static Queue<Argument> argumentQueue;
    private static ArrayList<Instruction> instructionQueue = new ArrayList<>();

    public InstructionHead(int enterCode, Queue<Argument> args){
        this.argumentQueue = args;
        switch(enterCode){
            case 0:
                System.out.println("Executing in Run Mode");
                debug = false;
                break;
            case 1:
                System.out.println("Executing in Debug Mode");
                debug = true;
                break;
        }
    }
    public void compile(){
        while(!argumentQueue.isEmpty()){
            try {
//                Argument arg = argumentQueue.poll();
//                Instruction instruction = new Instruction(instructionQueue.size(), arg.arg, arg.parameters);
//                instructionQueue.add(instruction);
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Compilation failed");
                return;
            }
            if(debug){
                debugScanner.nextLine();
            }
        }
    }
    public void run(){
        while(headPosition < instructionQueue.size()){
            try {
                instructionQueue.get(headPosition).execute(this);
            }
            catch(Exception e){
                e.printStackTrace();
                abort();
                exitCode = 1;
            }
            if(debug){
                debugScanner.nextLine();
            }
        }
        System.out.println("Ran with exit code " + exitCode);
    }
    public static void throwError(Error e){
        abort(e.toString());
    }
    public static void abort(){
        exitCode = 2;
        headPosition = instructionQueue.size();
    }
    public static void abort(String code){
        exitCode = 2;
        System.out.println(code);
        headPosition = instructionQueue.size();
    }
    protected void goToPosition(int position){
        headPosition = position;
    }
    protected void goToNext(){
        headPosition++;
    }
}
