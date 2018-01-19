package com.businessapp.logic;

import java.util.Arrays;

public class DoubleStack {
    double data[];
    private int top;

    public DoubleStack(){
        data = new double[100];
        top = -1;
    }
    public void push(double c){
        data[++top] = c;
    }

    public double pop(){
        return data[top--];

    }

    public boolean empty(){
        if(top==-1)
            return true;
        return false;
    }

    public void clear(){
        top=-1;
    }

    public void delete(){
        Arrays.fill(data, 0.0);
    }
}