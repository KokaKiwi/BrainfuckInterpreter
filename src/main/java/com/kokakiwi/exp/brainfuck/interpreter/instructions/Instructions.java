package com.kokakiwi.exp.brainfuck.interpreter.instructions;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

public class Instructions
{
    private final static Map<Character, Class<? extends Instruction>> instructions = new LinkedHashMap<Character, Class<? extends Instruction>>();
    private final static Map<Character, Object[]>                     parameters   = new LinkedHashMap<Character, Object[]>();
    
    static
    {
        register('>', IncrementPointerInstruction.class);
        register('<', DecrementPointerInstruction.class);
        register('+', IncrementArrayInstruction.class);
        register('-', DecrementArrayInstruction.class);
        register('.', OutputInstruction.class);
        register(',', InputInstruction.class);
        register('[', LoopInstruction.class, true);
        register(']', LoopInstruction.class, false);
    }
    
    public static void register(char c, Class<? extends Instruction> clazz,
            Object... params)
    {
        instructions.put(c, clazz);
        parameters.put(c, params);
    }
    
    public static Instruction get(char c) throws Exception
    {
        Object[] params = parameters.get(c);
        Class<?>[] classes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++)
        {
            classes[i] = params[i].getClass();
        }
        
        Class<? extends Instruction> clazz = instructions.get(c);
        Constructor<? extends Instruction> constructor = clazz
                .getDeclaredConstructor(classes);
        
        Instruction instruction = constructor.newInstance(params);
        
        return instruction;
    }
}
