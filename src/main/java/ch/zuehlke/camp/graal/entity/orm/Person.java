package ch.zuehlke.camp.graal.entity.orm;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Person extends PanacheEntity {
    public String firstname;
    public String lastname;
    public LocalDateTime created;

    public static Person findByName(String name){
        Person registration = find("lastname", name).firstResult();
        if (registration == null){
            registration = find("firstname", name).firstResult();
        }
        return registration;
    }

}