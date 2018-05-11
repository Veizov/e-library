package bg.tu.sofia.repository.custom.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public abstract class ABaseCustom {

    @PersistenceContext
    EntityManager em;

}
