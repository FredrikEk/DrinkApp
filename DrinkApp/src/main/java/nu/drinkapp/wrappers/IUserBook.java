/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.wrappers;

import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IUserBook extends IDAO<User, Long> {

    User findByName(String name);
    User findByMail(String email);
}
