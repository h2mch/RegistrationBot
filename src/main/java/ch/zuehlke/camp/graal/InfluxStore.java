package ch.zuehlke.camp.graal;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ch.zuehlke.camp.graal.entity.AppLifecycleEvent;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class InfluxStore {


    @ConfigProperty(name = "INFLUX_USER")
    private String influxUser;

    @ConfigProperty(name = "INFLUX_PASSWORD")
    private String influxPassword;

    @ConfigProperty(name = "INFLUX_DB")
    private String dbName;

    private AtomicBoolean darkSkyHealth = new AtomicBoolean(true);

    @Inject
    @RestClient
    private DarkSkyClient client;


    public void lifecycleEvent(long currentTimeMillis, AppLifecycleEvent event) {
        try (InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", influxUser, influxPassword)) {
            influxDB.setDatabase(dbName);
            influxDB.write(Point.measurement("lifecycle")
                    .time(currentTimeMillis, TimeUnit.MILLISECONDS)
                    .addField("event", event.name())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(every = "1m")
    public void storeWetter() {

        long timeToSet = System.currentTimeMillis();

        try {
            HashMap weather = client.getWeather("47.05048", "8.30635");
            Map currently = (Map) weather.get("currently");

            try (InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", influxUser, influxPassword)) {
                influxDB.setDatabase(dbName);


                influxDB.write(Point.measurement("weather")
                        .time(timeToSet, TimeUnit.MILLISECONDS)
                        .addField("summary", (String) currently.get("summary"))
                        .addField("temperature", ((BigDecimal) currently.get("temperature")).intValue())
                        .addField("pressure", ((BigDecimal) currently.get("pressure")).intValue())
                        .addField("windSpeed", ((BigDecimal) currently.get("windSpeed")).intValue())
                        .addField("windGust", ((BigDecimal) currently.get("windGust")).intValue())
                        .build());


                influxDB.write(Point.measurement("health")
                        .time(timeToSet, TimeUnit.MILLISECONDS)
                        .addField("darksky", true)
                        .build());

            }
        } catch (Exception e) {
            darkSkyHealth.set(false);
        }
    }


    @Scheduled(every = "10s")
    public void healthCheck() {
        try (InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", influxUser, influxPassword)) {
            influxDB.setDatabase(dbName);
            Pong response = influxDB.ping();

            Point health = Point.measurement("health")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("Health", true)
                    .addField("influx", response.isGood())
                    .addField("darkSky", darkSkyHealth.get())
                    .build();
            influxDB.write(health);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
