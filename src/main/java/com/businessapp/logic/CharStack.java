package com.businessapp.logic;

import java.util.Arrays;

public class CharStack {
    char data[];
    private int top;

    public CharStack(){
        data = new char[100];
        top = -1;
    }
    public void push(char c){
        data[++top] = c;
    }

    public char pop(){
        if(top<0)
            return'(';
        return data[top--];
    }

    public boolean empty(){
        if(top==-1)
            return true;
        return false;
    }
    public char peek(){
        if(top<0)
            return '(';
        return data[top];

    }
    public void clear(){
        top=-1;
    }

    public void delete(){
        Arrays.fill(data, ' ');
    }
}