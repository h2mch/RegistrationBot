package ch.zuehlke.camp.graal;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ch.zuehlke.camp.graal.entity.Registration;
import ch.zuehlke.camp.graal.entity.telegram.GetUpdates;
import ch.zuehlke.camp.graal.entity.telegram.Message;
import ch.zuehlke.camp.graal.entity.telegram.Update;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class TelegramStore {

    @ConfigProperty(name = "BOT_NAME")
    private String botName;

    @ConfigProperty(name = "BOT_URL")
    private String botUrl;

    @ConfigProperty(name = "BOT_TOKEN")
    private String botToken;

    @ConfigProperty(name = "STORE_CHAT_ID")
    private String storeChatId;

    private Set<Registration> notifications = new HashSet<>();

    private Map<String, Registration> registrations = new ConcurrentHashMap<>();

    @Inject
    @RestClient
    TelegramClient client;

    private AtomicInteger updateId = new AtomicInteger();

    public Registration get(String id) {
        return registrations.get(id);
    }

    public Collection<Registration> getNotifications() {
        return notifications;
    }

    public Collection<Registration> getRegistrations() {
        return registrations.values();
    }

    public void putNotification(Registration notification) {
        client.sendMessage(botToken, storeChatId, "notif|" + notification.getFirstname() + "|" + notification.getLastname());

    }
    public void clearStores() {
        registrations.clear();
        notifications.clear();
    }

    public void initStore() {
        GetUpdates getUpdates = client.getUpdates(botToken);
        List<Update> updates = getUpdates.getUpdates();
        for (Update update : updates) {
            updateId.set(update.getUpdateId());

            if (storeChatId.equals(update.getMessage().getFrom().getId().toString())) {
                String text = update.getMessage().getText();
                if (text.startsWith("notif|")) {
                    String[] split = text.split("|");
                    if (split.length == 3) {
                        Registration registration = new Registration();
                        registration.setFirstname(split[1]);
                        registration.setLastname(split[2]);
                        notifications.add(registration);
                    }
                }
            } else {
                Integer id = update.getMessage().getFrom().getId();
                Registration registration = registrations.get(id.toString());
                if (registration == null) {
                    registration = new Registration();
                }
                String firstName = update.getMessage().getFrom().getFirstName();
                registration.setFirstname(firstName);
                String lastName = update.getMessage().getFrom().getLastName();
                registration.setLastname(lastName);
                registration.setRegistered(true);
                registrations.put(id.toString(), registration);
            }

        }
        updates.forEach(update -> System.out.println("id: " + update.getUpdateId()));
    }


    @Scheduled(every = "10s")
    public void pollTelegram() {
        clearStores();
        initStore();
    }
}
