package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Step;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface IStepBook extends IDAO<Step, Long> {
    public void deleteAllByDrink(Drink drink);
}
