/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Type;
import com.DrinkApp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface ITypeBook extends IDAO<Type, Long> {
    public Type findByName(String name);
}
