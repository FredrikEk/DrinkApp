/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.auth.User;
import com.DrinkApp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fredrik
 */
@Local
public interface IUserBook extends IDAO<User, Long> {

    User getByName(String name);
    User getByMail(String email);
}
