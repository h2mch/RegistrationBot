package ch.zuehlke.camp.graal.entity.telegram;

import javax.json.bind.annotation.JsonbProperty;

public class Update {

    @JsonbProperty(value = "update_id")
    private Integer updateId;
    @JsonbProperty("message")
    private Message message;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
