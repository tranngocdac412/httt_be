package btlhttt.dto;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.jdbc.JdbcOperationsDependsOnPostProcessor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class ComputerError_MarkDto implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String computerErrorUuid;

    @Column(nullable = false)
    private String computerMarkUuid;

    @Column(nullable = false)
    private double value;

    public ComputerError_MarkDto() {
    }

    public ComputerError_MarkDto(String computerErrorUuid, String computerMarkUuid) {
        this.computerErrorUuid = computerErrorUuid;
        this.computerMarkUuid = computerMarkUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getComputerErrorUuid() {
        return computerErrorUuid;
    }

    public void setComputerErrorUuid(String computerErrorUuid) {
        this.computerErrorUuid = computerErrorUuid;
    }

    public String getComputerMarkUuid() {
        return computerMarkUuid;
    }

    public void setComputerMarkUuid(String computerMarkUuid) {
        this.computerMarkUuid = computerMarkUuid;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ComputerError_MarkDto{" +
                "uuid='" + uuid + '\'' +
                ", computerErrorUuid='" + computerErrorUuid + '\'' +
                ", computerMarkUuid='" + computerMarkUuid + '\'' +
                ", value=" + value +
                '}';
    }
}
