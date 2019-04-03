package ch.zuehlke.camp.graal.entity.telegram;

import javax.json.bind.annotation.JsonbProperty;

public class User {

    @JsonbProperty("id")
    private Integer id;
    @JsonbProperty("first_name")
    private String firstName;
    @JsonbProperty("is_bot")
    private Boolean isBot;
    @JsonbProperty("last_name")
    private String lastName;
    @JsonbProperty("username")
    private String userName;
    @JsonbProperty("language_code")
    private String languageCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getBot() {
        return isBot;
    }

    public void setBot(Boolean bot) {
        isBot = bot;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
