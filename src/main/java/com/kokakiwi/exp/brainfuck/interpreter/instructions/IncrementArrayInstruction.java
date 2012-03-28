package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public class IncrementArrayInstruction implements Instruction
{
    public void execute(Context context)
    {
        context.incrementArray();
    }
}
