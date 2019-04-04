package ch.zuehlke.camp.graal.entity.orm;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Event extends PanacheEntity {

    public String name;

    public LocalDateTime start;
    public Duration duration;

    public LocalDateTime created;
}