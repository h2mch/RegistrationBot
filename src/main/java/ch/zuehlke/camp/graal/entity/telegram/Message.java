package ch.zuehlke.camp.graal.entity.telegram;


import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class Message {

    @JsonbProperty("message_id")
    private Integer messageId;
    @JsonbProperty("from")
    private User from;
    @JsonbProperty("date")
    private Integer date;
    @JsonbProperty("chat")
    private Chat chat;
    @JsonbProperty("text")
    private String text;
    @JsonbProperty("entities")
    private List<MessageEntity> entities;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<MessageEntity> entities) {
        this.entities = entities;
    }
}
