package spring.boot.servicephones.command.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "phones")
public class Phone {
    @Id
    private String id;
    private String name;
    private Boolean enable;

    // Constructor
    public Phone() {
        this.id = UUID.randomUUID().toString();
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Phone(id=" + id + ", name=" + name + ", enable=" + enable + ")";
    }
}

