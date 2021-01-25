package com.example.unittestcase;

import junit.framework.TestCase;

import org.junit.Test;

public class MyClassTest extends TestCase {

    // create MyClass object
    MyClass myClass;

    public void setUp() throws Exception {
        super.setUp();
        // set up MyClass
        myClass = new MyClass();
    }

    // test compare function
    public void testCompare() throws Exception{
        int a = 10;
        int b = 20;

        // calling compare function
        boolean f = myClass.compare(a,b);
     // boolean expValue = false;

        // verifying data
         assertFalse(f);
       // assertEquals(false,f);
    }

    @Test
    public void testSum() throws Exception{
        int a = 10;
        int b = 20;
        int result = 30;

        // calling sum function
        int s = MyClass.sum(a,b);

        // verifying data
        assertEquals(s,result);
    }

    public void testGetString() throws Exception {
        String expString = "Hello World";

        // calling getString function
        String strResult = myClass.getString();

        // verifying data
        assertEquals(expString,strResult);
    }
}