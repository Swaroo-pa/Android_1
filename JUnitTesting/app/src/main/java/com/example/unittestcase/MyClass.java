package com.example.unittestcase;

public class MyClass {

    public MyClass(){

    }

    // for comparing two integers
    public  boolean compare(int a, int b){
        if(a >= b){
            return true;
        }
        else {
            return false;
        }
    }

    // for adding of two integers
    public static int sum(int a, int b){

        return (a+b);
    }

    // for passing a string
    public String getString(){

        return "Hello World";
    }

}
