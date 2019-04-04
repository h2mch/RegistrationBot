package ch.zuehlke.camp.graal;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@ApplicationScoped
public class PostgresStore {

    @Transactional
    public void store(PanacheEntity entity) {
        entity.persist();
    }
}
