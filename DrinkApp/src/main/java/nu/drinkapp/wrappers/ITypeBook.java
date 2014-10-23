/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.wrappers;

import nu.drinkapp.core.Type;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface ITypeBook extends IDAO<Type, Long> {
    public Type findByName(String name);
    public void deleteDrinkTypes(String username, String drinkname);
}
