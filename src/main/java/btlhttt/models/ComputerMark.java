package btlhttt.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "computer_mark")
public class ComputerMark {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String description;

    public ComputerMark() {
    }

    public ComputerMark(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ComputerMark{" +
                "uuid='" + uuid + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
