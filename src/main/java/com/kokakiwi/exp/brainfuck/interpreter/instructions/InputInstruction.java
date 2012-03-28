package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import java.io.IOException;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public class InputInstruction implements Instruction
{
    
    public void execute(Context context)
    {
        try
        {
            context.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
