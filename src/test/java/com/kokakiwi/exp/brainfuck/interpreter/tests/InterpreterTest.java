package com.kokakiwi.exp.brainfuck.interpreter.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import org.junit.Test;

import com.kokakiwi.exp.brainfuck.interpreter.Interpreter;

public class InterpreterTest
{
    public final static boolean DUMP = true;
    
    @Test
    public void test() throws IOException
    {
        OutputStream fileOutput = new FileOutputStream(new File("output.txt"));
        OutputStream out = new DoubleOutput(System.out, fileOutput);
        
        String filename = "test.bf";
        Interpreter interpreter = new Interpreter(new String[] { filename });
        interpreter.init();
        interpreter.getContext().setOut(out);
        interpreter.run();
        
        if (DUMP)
        {
            PrintWriter writer = new PrintWriter(fileOutput);
            writer.println();
            writer.println("=== DEBUG TRACE ===");
            writer.println("Memory dump: " + Arrays.toString(interpreter.getContext()
                    .getArrayBytes()));
        }
    }
    
    public static class DoubleOutput extends OutputStream
    {
        private final OutputStream out1;
        private final OutputStream out2;
        
        public DoubleOutput(OutputStream out1, OutputStream out2)
        {
            super();
            this.out1 = out1;
            this.out2 = out2;
        }
        
        @Override
        public void write(byte[] b) throws IOException
        {
            out1.write(b);
            out2.write(b);
        }
        
        @Override
        public void write(byte[] b, int off, int len) throws IOException
        {
            out1.write(b, off, len);
            out2.write(b, off, len);
        }
        
        @Override
        public void flush() throws IOException
        {
            out1.flush();
            out2.flush();
        }
        
        @Override
        public void close() throws IOException
        {
            out1.close();
            out2.close();
        }
        
        @Override
        public void write(int b) throws IOException
        {
            out1.write(b);
            out2.write(b);
        }
    }
}
