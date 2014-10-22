/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.Core.Rating;
import com.DrinkApp.auth.User;
import com.DrinkApp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fredrik
 */
@Local
public interface IRatingBook extends IDAO<Rating, Long> {
    public List<Rating> findByDrink(Drink drink);
    public List<Rating> findByUser(User user);
    public Rating findByDrinkAndUser(Drink drink, User user);
    public Double getAverageRating(Drink drink);
    public void deleteRatings(Drink drink);
}
