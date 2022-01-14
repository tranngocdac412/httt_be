package btlhttt.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "computer_error")
public class ComputerError {
    //    @SequenceGenerator(
//            name = "computerErrorSequence",
//            sequenceName = "computerErrorSequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "computerErrorSequence"
//    )

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private String solution;

    public ComputerError() {
    }

    public ComputerError(String description, String solution) {
        this.description = description;
        this.solution = solution;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "ComputerError{" +
                "uuid='" + uuid + '\'' +
                ", description='" + description + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
