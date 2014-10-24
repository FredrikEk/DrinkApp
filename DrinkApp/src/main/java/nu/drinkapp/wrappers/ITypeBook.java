package nu.drinkapp.wrappers;

import nu.drinkapp.core.Type;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface ITypeBook extends IDAO<Type, Long> {
    public Type findByName(String name);
    public void deleteDrinkTypes(String username, String drinkname);
}
