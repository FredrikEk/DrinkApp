package nu.drinkapp.wrappers;

import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

/**
 * Interface for UserBook
 */

@Local
public interface IUserBook extends IDAO<User, Long> {

    User findByName(String name);
    User findByMail(String email);
}
