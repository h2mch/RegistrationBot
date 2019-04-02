package ch.zuehlke.camp.graal;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

import ch.zuehlke.camp.graal.entity.Registration;

@ApplicationScoped
public class RegistrationService {

    private Map<String, Registration> store = new ConcurrentHashMap<>();

    public String register(Registration registration){
        String id = UUID.randomUUID().toString();
        registration.setId(id);
        store.put(id, registration);
        return id;
    }


    public Registration getRegistration(String id){
        return store.get(id);
    }

    public Collection<Registration> getRegistrations(){
        return store.values();
    }

    public Registration update(String uuid, Registration registration) {
        return store.put(uuid, registration);
    }
}
