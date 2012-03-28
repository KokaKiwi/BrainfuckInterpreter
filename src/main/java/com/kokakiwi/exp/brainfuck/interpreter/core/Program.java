package com.kokakiwi.exp.brainfuck.interpreter.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.kokakiwi.exp.brainfuck.interpreter.instructions.Instruction;
import com.kokakiwi.exp.brainfuck.interpreter.instructions.Instructions;
import com.kokakiwi.exp.brainfuck.interpreter.utils.Compiler;

public class Program
{
    private final char[]            chars;
    
    private final List<Instruction> instructions = new LinkedList<Instruction>();
    
    public Program(Instructions instructionsSet, char[] chars)
    {
        this.chars = chars;
        
        for (char c : chars)
        {
            try
            {
                Instruction instruction = instructionsSet.get(c);
                instructions.add(instruction);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public char[] getChars()
    {
        return chars;
    }
    
    public List<Instruction> getInstructions()
    {
        return instructions;
    }
    
    public static Program load(Instructions instructionsSet, File file) throws IOException
    {
        return load(instructionsSet, new FileInputStream(file));
    }
    
    public static Program load(Instructions instructionsSet, InputStream in) throws IOException
    {
        char[] c = IOUtils.toCharArray(in);
        
        return load(instructionsSet, c);
    }
    
    public static Program load(Instructions instructionsSet, char[] c)
    {
        Program program = new Program(instructionsSet, Compiler.compile(c));
        
        return program;
    }
}
