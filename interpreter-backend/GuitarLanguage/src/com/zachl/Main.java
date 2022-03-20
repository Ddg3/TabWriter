package com.zachl;

import com.zachl.interpreters.ArgumentInterpreter;
import com.zachl.objects.execution.Instruction;
import com.zachl.objects.execution.InstructionHead;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final int ENTER_CODE = 0;
    private static final String FILE_PATH = "res/tempTab.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        Constants.init();
        ArgumentInterpreter arg = new ArgumentInterpreter(file);
        Queue<Instruction> instructions = new LinkedList<>();
        arg.interpretArguments(instructions);
        InstructionHead head = new InstructionHead(ENTER_CODE, instructions);

        //head.compile();
        head.run();
    }
}
