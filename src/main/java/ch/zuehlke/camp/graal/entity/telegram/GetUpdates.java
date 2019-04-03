package ch.zuehlke.camp.graal.entity.telegram;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class GetUpdates {

    @JsonbProperty("ok")
    private boolean ok;

    @JsonbProperty("result")
    private List<Update> updates;


    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Update> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update> updates) {
        this.updates = updates;
    }
}
