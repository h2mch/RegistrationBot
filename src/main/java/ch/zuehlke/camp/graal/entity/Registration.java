package ch.zuehlke.camp.graal.entity;

import javax.validation.constraints.NotEmpty;

public class Registration {

    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    private String id;


    public String getFirstname() {
        return firstname;
    }

    public String getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
