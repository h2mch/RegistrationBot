package ch.zuehlke.camp.graal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import ch.zuehlke.camp.graal.entity.AppLifecycleEvent;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifecycle {

    @Inject
    InfluxStore influxStore;

    void onStart(@Observes StartupEvent ev) {
  //      influxStore.lifecycleEvent(System.currentTimeMillis(), AppLifecycleEvent.STARTUP);
    }

    void onStop(@Observes ShutdownEvent ev) {
   //     influxStore.lifecycleEvent(System.currentTimeMillis(), AppLifecycleEvent.SHUTDOWN);
    }
}
