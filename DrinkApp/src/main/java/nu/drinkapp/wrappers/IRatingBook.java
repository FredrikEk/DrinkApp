package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Rating;
import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for RatingBook
 */

@Local
public interface IRatingBook extends IDAO<Rating, Long> {
    public List<Rating> findByDrink(Drink drink);
    public List<Rating> findByUser(User user);
    public Rating findByDrinkAndUser(Drink drink, User user);
    public Double getAverageRating(Drink drink);
    public void deleteRatings(Drink drink);
}
