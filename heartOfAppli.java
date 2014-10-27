    @Override
    public List<Object[]> searchByNameAndIngredient(String drinkname, List<String> ingredients){
        String s = "";
        for(int i = 0; i < ingredients.size(); i++) {
            s += "'" + ingredients.get(i) + "'";
            if(i != ingredients.size() - 1) {
                s += ",";
            }
        }
        //The Drinksearch query with subqueries.
        //The main query that finds out if a drink contains all the submitted ingredients
        //and/or how many ingredients that are missing
        Query tq = em.createNativeQuery("SELECT d.drinkname, d.user_username, (ipd.NROFINGREDIENTS - da.drinkcount) AS Counters \n" +
                                               //Subquery to find which of the submitted ingredients a drink contains.
                                        "FROM (SELECT di.DRINKNAME AS Drinkname, COUNT(*) AS drinkCount \n" +
                                              "FROM DrinkIngredient di \n" +
                                              "WHERE di.INGREDIENT_NAME IN (" + s + ") \n" +
                                              "GROUP BY di.DRINKNAME) da, \n" + 
                                               //Subquery to find total number of ingredients per drink
                                              "(SELECT di.DRINKNAME AS Drinkname, COUNT(*) AS nrOfIngredients \n" +
                                              "FROM DrinkIngredient di \n" +
                                              "GROUP BY di.DRINKNAME) ipd, Drink d \n" +
                                        "WHERE da.drinkname LIKE '%[" + drinkname + "]%' AND da.drinkname = ipd.DRINKNAME AND d.drinkname = da.drinkname \n" +
                                        "ORDER BY Counters ASC");
        return tq.getResultList();

    }
	
	
	