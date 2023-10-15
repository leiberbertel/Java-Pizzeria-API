package com.leiber.pizza.persistence.audit;

import com.leiber.pizza.persistence.entity.Pizza;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private Pizza currentValue;

    @PostLoad
    public void postLoad(Pizza entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void OnPostPersist(Pizza entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE:" + this.currentValue.toString());
        System.out.println("NEW VALUE:" + entity.toString());
    }

    @PreRemove
    public void OnPreDelete(Pizza entity) {
        System.out.println(entity.toString());
    }
}
