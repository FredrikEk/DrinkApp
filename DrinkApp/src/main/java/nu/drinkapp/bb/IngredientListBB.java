package nu.drinkapp.bb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Named
@ViewScoped
public class IngredientListBB implements Serializable{

    private Long id;
    
    @NotNull
    @Size(min = 4, max = 20, message="Must use 4-20 chars")
    private String name;


    @PostConstruct
    public void post() {
    }

    @PreDestroy
    public void pre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddProductBB{" + "id=" + id + ", name=" + name + '}';
    }  
}
