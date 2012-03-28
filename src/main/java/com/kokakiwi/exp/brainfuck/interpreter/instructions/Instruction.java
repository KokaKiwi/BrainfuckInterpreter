package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public interface Instruction
{
    public void execute(Context context);
}
