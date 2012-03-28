package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;

public class LoopInstruction implements Instruction
{
    private final boolean start;
    
    private int           jumpPointer = -1;
    
    public LoopInstruction(Boolean start)
    {
        this.start = start;
    }
    
    public void execute(Context context)
    {
        byte data = context.getArray();
        
        if (start)
        {
            if (jumpPointer == -1)
            {
                jumpPointer = searchEndPointer(context);
            }
            
            if (jumpPointer > -1)
            {
                if (data == 0)
                {
                    context.setReadPointer(jumpPointer);
                }
            }
        }
        else
        {
            if (jumpPointer == -1)
            {
                jumpPointer = searchStartPointer(context);
            }
            
            if (jumpPointer > -1)
            {
                if (data != 0)
                {
                    context.setReadPointer(jumpPointer);
                }
            }
        }
    }
    
    private int searchStartPointer(Context context)
    {
        return searchJumpPointer(context, ']', '[', -1);
    }
    
    private int searchEndPointer(Context context)
    {
        return searchJumpPointer(context, '[', ']', 1);
    }
    
    private int searchJumpPointer(Context context, char start, char end,
            int delta)
    {
        int pointer = context.getReadPointer();
        int count = 0;
        boolean search = true;
        
        while (search)
        {
            pointer += delta;
            if (pointer > -1
                    && pointer < context.getProgram().getChars().length)
            {
                char c = context.getProgram().getChars()[pointer];
                if (c == start)
                {
                    count++;
                }
                else if (c == end)
                {
                    if (count > 0)
                    {
                        count--;
                    }
                    else
                    {
                        search = false;
                    }
                }
            }
            else
            {
                search = false;
            }
        }
        
        return pointer;
    }
}
