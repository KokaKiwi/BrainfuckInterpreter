package com.kokakiwi.exp.brainfuck.interpreter.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.kokakiwi.exp.brainfuck.interpreter.instructions.Instruction;

public class Context
{
    private Program            program = null;
    
    private int                readPointer;
    private int                pointer;
    private byte[]             array;
    
    private final InputStream  in;
    private final OutputStream out;
    
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
    
    public boolean hasNext()
    {
        return readPointer < program.getInstructions().size();
    }
    
    public Instruction next()
    {
        Instruction instruction = program.getInstructions().get(readPointer);
        
        return instruction;
    }
    
    public void reset()
    {
        readPointer = 0;
        pointer = 0;
        
        int size = 128;
        array = new byte[size];
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
    }
    
    public void incrementPointer()
    {
        this.pointer++;
    }
    
    public void decrementPointer()
    {
        this.pointer--;
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
        return array;
    }
    
    public byte getArray()
    {
        return array[pointer];
    }
    
    public void incrementArray()
    {
        array[pointer]++;
    }
    
    public void decrementArray()
    {
        array[pointer]--;
    }
    
    public void write() throws IOException
    {
        out.write(((int) array[pointer]) & 0xff);
        out.flush();
    }
    
    public void read() throws IOException
    {
        byte b = (byte) (in.read() & 0xff);
        array[pointer] = b;
    }
    
    public InputStream getIn()
    {
        return in;
    }
    
    public OutputStream getOut()
    {
        return out;
    }
}
