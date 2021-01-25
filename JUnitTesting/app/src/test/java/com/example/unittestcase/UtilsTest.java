package com.example.unittestcase;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UtilsTest extends TestCase {

   /*  Utils utils;
    *
    * public void setUp() throws Exception {
    *   super.setUp();
    *  utils = new Utils();
    * }
    */

    @Test
    public void testCheckEmailForValidity() {
        String testEmail = "swaroopamammu1997@gmail.com";

      /*  Assert.assertThat(String.format("Email validity test failed for %s", testEmail),
       *       utils.checkEmailForValidity(testEmail), is(true));
       */
        
      // for validating email
        assertEquals("Email validity test failed ",true, Utils.checkEmailForValidity(testEmail) );
    }
}