package ch.zuehlke.camp.graal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifecycle {

    @Inject
    TelegramStore telegramStore;

    void onStart(@Observes StartupEvent ev) {
    //    telegramStore.initStore();
    }

    void onStop(@Observes ShutdownEvent ev) {
    }
}
