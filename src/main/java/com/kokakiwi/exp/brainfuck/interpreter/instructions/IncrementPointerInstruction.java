package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public class IncrementPointerInstruction implements Instruction
{
    public void execute(Context context)
    {
        context.incrementPointer();
    }
}
