package com.zachl.objects.execution;

import java.util.*;

public class InstructionHead {
    private static boolean debug;
    private static Scanner debugScanner;

    private static int exitCode;
    private static int headPosition = 0;
    private static final InstructionQueue instructionQueue = new InstructionQueue();

    public InstructionHead(int enterCode, Queue<Instruction> instructions){
        instructionQueue.append(instructions);
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
    public void run(){
        while(headPosition < instructionQueue.size()){
            try {
                instructionQueue.instructionAt(headPosition).execute(this);
                goToNext();
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
