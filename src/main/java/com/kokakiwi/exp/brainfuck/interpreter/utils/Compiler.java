package com.kokakiwi.exp.brainfuck.interpreter.utils;

import java.util.LinkedList;
import java.util.List;

public class Compiler
{
    public static char[] compile(char[] chars)
    {
        int size = 0;
        List<Character> c = new LinkedList<Character>();
        boolean ignore = false;
        
        for (int i = 0; i < chars.length; i++)
        {
            char ch = chars[i];
            
            switch (ch)
            {
                case '\n':
                case '\r':
                    ignore = false;
                    break;
                
                case '#':
                    ignore = true;
                    
                case ' ':
                case '\t':
                    break;
                
                default:
                    if (!ignore)
                    {
                        c.add(ch);
                        size++;
                    }
                    break;
            }
        }
        
        char[] chs = new char[size];
        for (int i = 0; i < size; i++)
        {
            char ch = c.get(i);
            chs[i] = ch;
        }
        
        return chs;
    }
}
