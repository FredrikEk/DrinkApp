/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.auth;

public class SanitizedInput {
    
    /*
    * Because of all our SQL-queries not being prepared statements.
    * This could come in handy for filtering unwanted characters in databasecalls.
    */
    public static boolean sanitizeInput(String checkString){
        String[] arr = checkString.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2);
        return arr.length > 1;
    }
}
