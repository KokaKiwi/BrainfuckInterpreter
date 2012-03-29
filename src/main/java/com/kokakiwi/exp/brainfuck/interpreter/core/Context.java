package com.kokakiwi.exp.brainfuck.interpreter.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import com.kokakiwi.exp.brainfuck.interpreter.instructions.Instruction;

public class Context
{
    public final static int MEMORYSIZE = 1048 * 16;           // 16 Ko
                                                               
    private Program         program    = null;
    
    private int             readPointer;
    private int             pointer;
    private final byte[]    memory     = new byte[MEMORYSIZE];
    
    private InputStream     in;
    private OutputStream    out;
    
    public Context(InputStream in, OutputStream out)
    {
        this.in = in;
        this.out = out;
        
        reset();
    }
    
    public void run()
    {
        if (program != null)
        {
            while (hasNext())
            {
                Instruction instruction = next();
                
                if (instruction != null)
                {
                    instruction.execute(this);
                }
                
                readPointer++;
            }
        }
        else
        {
            throw new NullPointerException("program");
        }
    }
    
    public void reset()
    {
        readPointer = 0;
        pointer = 0;
        
        Arrays.fill(memory, (byte) 0);
    }
    
    public boolean hasNext()
    {
        return readPointer < program.getInstructions().size();
    }
    
    public Instruction next()
    {
        Instruction instruction = program.getInstructions().get(readPointer);
        
        return instruction;
    }
    
    public Program getProgram()
    {
        return program;
    }
    
    public void setProgram(Program program)
    {
        this.program = program;
    }
    
    public int getPointer()
    {
        return pointer;
    }
    
    public void setPointer(int pointer)
    {
        this.pointer = pointer;
        
        fixPointer();
    }
    
    public void incrementPointer()
    {
        this.pointer++;
        
        fixPointer();
    }
    
    public void decrementPointer()
    {
        this.pointer--;
        
        fixPointer();
    }
    
    private void fixPointer()
    {
        while (this.pointer < 0)
        {
            this.pointer += MEMORYSIZE;
        }
        while (this.pointer >= MEMORYSIZE)
        {
            this.pointer -= MEMORYSIZE;
        }
    }
    
    public int getReadPointer()
    {
        return readPointer;
    }
    
    public void setReadPointer(int readPointer)
    {
        this.readPointer = readPointer;
        
        if (this.readPointer < 0)
        {
            this.readPointer = 0;
        }
    }
    
    public byte[] getArrayBytes()
    {
        return memory;
    }
    
    public byte getArray()
    {
        return memory[pointer];
    }
    
    public void incrementArray()
    {
        memory[pointer]++;
    }
    
    public void decrementArray()
    {
        memory[pointer]--;
    }
    
    public void write() throws IOException
    {
        out.write(((int) memory[pointer]) & 0xff);
        out.flush();
    }
    
    public void read() throws IOException
    {
        byte b = (byte) (in.read() & 0xff);
        memory[pointer] = b;
    }
    
    public InputStream getIn()
    {
        return in;
    }
    
    public void setIn(InputStream in)
    {
        this.in = in;
    }
    
    public OutputStream getOut()
    {
        return out;
    }
    
    public void setOut(OutputStream out)
    {
        this.out = out;
    }
}
