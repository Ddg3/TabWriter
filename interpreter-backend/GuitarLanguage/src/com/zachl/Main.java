package com.zachl;

import com.zachl.interpreters.ArgumentInterpreter;
import com.zachl.objects.execution.InstructionHead;

import java.io.File;

public class Main {
    private static final int ENTER_CODE = 0;
    private static final String FILE_PATH = "res/tempTab.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        Constants.init();
        ArgumentInterpreter arg = new ArgumentInterpreter(file);
        InstructionHead head = new InstructionHead(ENTER_CODE, arg.interpretArguments());

        head.compile();
        head.run();
    }
}
