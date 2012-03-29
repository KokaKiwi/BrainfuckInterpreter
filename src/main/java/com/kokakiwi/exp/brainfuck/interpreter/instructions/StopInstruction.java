package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public class StopInstruction implements Instruction
{
    
    public void execute(Context context)
    {
        context.setReadPointer(context.getProgram().getChars().length);
    }
    
}
