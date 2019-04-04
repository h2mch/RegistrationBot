package ch.zuehlke.camp.graal.entity.orm;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Registration extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    public Person person;
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    public Event event;
    public String notice;
    public LocalDateTime created;
    public RegistrationState registrationState;


}