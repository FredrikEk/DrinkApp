package nu.drinkapp.core;

import nu.drinkapp.wrappers.ITypeBook;
import nu.drinkapp.wrappers.IIngredientBook;
import nu.drinkapp.wrappers.IUserBook;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.wrappers.IDrinkIngredientBook;
import nu.drinkapp.wrappers.IFavouriteBook;
import nu.drinkapp.wrappers.IRatingBook;
import nu.drinkapp.wrappers.IStepBook;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * A Bar to hold all the interfaces containing skeleton code for all
 * Entity-books.
 *
 */
@ApplicationScoped
public class Bar implements IBar {

    //@EJB
    @Inject
    private IDrinkBook drinkBook;
    @Inject
    private IUserBook userBook;
    @Inject
    private IIngredientBook ingredientBook;
    @Inject
    private ITypeBook typeBook;
    @Inject
    private IDrinkIngredientBook drinkIngredientBook;
    @Inject
    private IStepBook stepBook;
    @Inject
    private IRatingBook ratingBook;
    @Inject
    private IFavouriteBook favouriteBook;

    public Bar() {
    }

    public static IBar newInstance() {
        return new Bar();
    }

    @Override
    public IDrinkBook getDrinkBook() {
        return drinkBook;
    }

    @Override
    public IIngredientBook getIngredientBook() {
        return ingredientBook;
    }

    @Override
    public IUserBook getUserBook() {
        return userBook;
    }

    @Override
    public ITypeBook getTypeBook() {
        return typeBook;
    }

    @Override
    public IDrinkIngredientBook getDrinkIngredientBook() {
        return drinkIngredientBook;
    }

    @Override
    public IStepBook getStepBook() {
        return stepBook;
    }

    @Override
    public IRatingBook getRatingBook() {
        return ratingBook;
    }

    @Override
    public IFavouriteBook getFavouriteBook() {
        return favouriteBook;
    }
}
