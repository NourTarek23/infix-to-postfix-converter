package com.example.ds_interface;

public class StackCharacter {

    private char[] array;
    private int top;
    private int size;

    //implementations
    StackCharacter(int size)
    {
        this.size = size;
        array = new char[size];
        top = -1;
    }

    public StackCharacter()
    {
        size = 10;
        array = new char[size];
        top = -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getSize(){return size;}

    public char peek(){
        return array[top];
    }
    public boolean push(char data)
    {
        if (!isFull())
        {
            array[++top] = data;
            return true;
        }
        else
        {
            System.out.println("stack is full");
            return false;
        }
    }
    public char pop()
    {
        if (!isEmpty())
        {
            return array[top--];
        }
        else
            return 0;
    }
}
