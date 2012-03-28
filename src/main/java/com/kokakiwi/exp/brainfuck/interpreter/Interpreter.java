package com.kokakiwi.exp.brainfuck.interpreter;

import java.io.File;
import java.io.IOException;

import com.kokakiwi.exp.brainfuck.interpreter.core.Context;
import com.kokakiwi.exp.brainfuck.interpreter.core.Program;

public class Interpreter
{
    private File    file    = null;
    private Context context = null;
    
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
    
    public void run()
    {
        try
        {
            Program program = Program.load(file);
            context = new Context(System.in, System.out);
            context.setProgram(program);
            
            context.run();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public File getFile()
    {
        return file;
    }
    
    public void setFile(File file)
    {
        this.file = file;
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
        new Interpreter(args).run();
    }
    
}
