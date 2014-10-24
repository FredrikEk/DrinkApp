package nu.drinkapp.auth;

public class SanitizedInput {
    
    /*
    * Because of all our SQL-queries not being prepared statements.
    * This could come in handy for filtering unwanted characters in databasecalls.
    */
    public static boolean sanitizeInput(String checkString){
        String[] arr = checkString.split("[~#*+%{}<>\\[\\]|\"\\_^]", 2);
        return !(arr.length > 1);
    }
}
