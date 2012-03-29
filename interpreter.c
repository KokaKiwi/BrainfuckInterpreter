/*
    Inspired from https://github.com/FabianM/Brainfuck
*/

#include <stdio.h>
#include <stdlib.h>

#define MEMORYSIZE 2048

void interpret(char *filename)
{
    char c;                 // Current char
    FILE* file = NULL;      // File to read
    int size;               // File size
    int char_pointer = 0;   // Chars pointer
    int memory[MEMORYSIZE]; // Memory
    int pointer = 0;        // Memory pointer
    int loop = 0;           // Loop counter

    file = fopen(filename, "r");

    // Read file
    fseek(file, 0, SEEK_END);
    size = ftell(file);
    fseek(file, 0, SEEK_SET);

    char chars[size];
    while ((c = fgetc(file)) != EOF)
    {
        chars[pointer++] = (char) c;
    }

    fclose(file);

    pointer = 0;

    // Clear memory.
    while(pointer < MEMORYSIZE)
    {
        memory[pointer] = 0;
        pointer++;
    }

    pointer = 0;

    // RUN
    while(char_pointer < sizeof(chars))
    {
        switch(chars[char_pointer])
        {
            case '>':
                pointer++;
                break;

            case '<':
                pointer--;
                break;

            case '+':
                memory[pointer]++;
                break;

            case '-':
                memory[pointer]--;
                break;

            case '.':
                putchar(memory[pointer]);
                fflush(stdout);
                break;

            case ',':
                memory[pointer] = (int) getchar();
                break;

            case '[':
                if(memory[pointer] == 0)
                {
                    loop = 1;
                    while(loop > 0)
                    {
                        c = chars[++char_pointer];
                        if(c == '[')
                        {
                            loop++;
                        }
                        else if(c == ']')
                        {
                            loop--;
                        }
                    }
                }
                break;

            case ']':
                if(memory[pointer] != 0)
                {
                    loop = 1;
                    while(loop > 0)
                    {
                        c = chars[--char_pointer];
                        if(c == '[')
                        {
                            loop--;
                        }
                        else if(c == ']')
                        {
                            loop++;
                        }
                    }
                    char_pointer--;
                }
                break;

            case '#':
                while (chars[++char_pointer] != '\n');
                break;
        }

        char_pointer++;
    }
}

int main(int argc, char *argv[])
{
    if(argc < 2)
    {
        printf("Usage: interpreter <filename");
        return EXIT_FAILURE;
    }

    interpret(argv[1]);

    return EXIT_SUCCESS;
}
