package com.kokakiwi.exp.brainfuck.interpreter.tests;

import org.junit.Test;

import com.kokakiwi.exp.brainfuck.interpreter.Interpreter;

public class InterpreterTest
{
    
    @Test
    public void test()
    {
        String filename = "test.bf";
        Interpreter interpreter = new Interpreter(new String[] { filename });
        interpreter.run();
    }
    
}
