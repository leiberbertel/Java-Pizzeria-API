package com.leiber.pizza.persistence.audit;

import com.leiber.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void OnPostPersist(PizzaEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE:" + this.currentValue.toString());
        System.out.println("NEW VALUE:" + entity.toString());
    }

    @PreRemove
    public void OnPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }
}
