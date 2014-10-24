package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Favourite;
import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for FavouriteBook
 */

@Local
public interface IFavouriteBook extends IDAO<Favourite, Long> {
    public List<Favourite> findByUser(String username);
    public Favourite findByDrinkAndUser(Drink drink, User user);
    public void deleteFavourites(Drink drink);
    public void deleteByUserAndDrink(User user, Drink drink);
}