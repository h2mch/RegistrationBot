package ch.zuehlke.camp.graal;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifecycle {

    @Inject
    Store store;

    void onStart(@Observes StartupEvent ev) {
    //    store.initStore();
    }

    void onStop(@Observes ShutdownEvent ev) {
    }
}
