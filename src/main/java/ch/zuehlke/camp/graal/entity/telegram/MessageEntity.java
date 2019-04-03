package ch.zuehlke.camp.graal.entity.telegram;

import javax.json.bind.annotation.JsonbProperty;

public class MessageEntity {

    @JsonbProperty("type")
    private String type;
    @JsonbProperty("offset")
    private Integer offset;
    @JsonbProperty("length")
    private Integer length;
    @JsonbProperty("url")
    private String url;
    @JsonbProperty("user")
    private User user;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
