package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import java.io.IOException;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public class OutputInstruction implements Instruction
{
    
    public void execute(Context context)
    {
        try
        {
            context.write();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
