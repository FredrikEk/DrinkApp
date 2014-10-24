package nu.drinkapp.core;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*
 * The Type model. Stores different types that drinks can have
 * e.g sour, sweet, strong etc
 */

@Entity
@NamedQueries({@NamedQuery(name = "Type.findByName", query = "SELECT t FROM Type t WHERE t.name = :name")})
public class Type implements Serializable {

    @Column
    @Id
    private String name;
    
    public Type(){
        this.name = "";
     
    }
    
    public Type(String name) {
        this.name = name;
        }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Type{ name=" + name  + '}';
    }
}
