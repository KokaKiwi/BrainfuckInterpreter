package com.kokakiwi.exp.brainfuck.interpreter;

import java.io.File;
import java.io.IOException;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;
import com.kokakiwi.exp.brainfuck.interpreter.core.Program;
import com.kokakiwi.exp.brainfuck.interpreter.instructions.Instructions;

public class Interpreter
{
    private File         file            = null;
    private Context      context         = null;
    
    private Instructions instructionsSet = new Instructions();
    
    public Interpreter(String[] args)
    {
        if (args.length > 0)
        {
            String filename = args[0];
            file = new File(filename);
            
        }
        else
        {
            printHelp();
        }
    }
    
    public void init()
    {
        try
        {
            Program program = Program.load(instructionsSet, file);
            context = new Context(System.in, System.out);
            context.setProgram(program);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        context.run();
    }
    
    public File getFile()
    {
        return file;
    }
    
    public void setFile(File file)
    {
        this.file = file;
    }
    
    public Instructions getInstructionsSet()
    {
        return instructionsSet;
    }
    
    public void setInstructionsSet(Instructions instructionsSet)
    {
        this.instructionsSet = instructionsSet;
    }
    
    public Context getContext()
    {
        return context;
    }
    
    public static void printHelp()
    {
        System.out.println("=============== Usage guide ==================");
        System.out.println("java -jar brainfuck-interpreter.jar <filename>");
        System.out.println("==============================================");
    }
    
    public static void main(String[] args)
    {
        Interpreter interpreter = new Interpreter(args);
        interpreter.init();
        interpreter.run();
    }
    
}
