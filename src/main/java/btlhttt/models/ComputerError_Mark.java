package btlhttt.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "computer_error_mark")
public class ComputerError_Mark {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "computer_error_uuid")
    private ComputerError computerError;

    @ManyToOne
    @JoinColumn(name = "computer_mark_uuid")
    private ComputerMark computerMark;

    @Column(nullable = false)
    private double value;

    public ComputerError_Mark() {
    }

    public ComputerError_Mark(ComputerError computerError, ComputerMark computerMark, double value) {
        this.computerError = computerError;
        this.computerMark = computerMark;
        this.value = value;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ComputerError getComputerError() {
        return computerError;
    }

    public void setComputerError(ComputerError computerError) {
        this.computerError = computerError;
    }

    public ComputerMark getComputerMark() {
        return computerMark;
    }

    public void setComputerMark(ComputerMark computerMark) {
        this.computerMark = computerMark;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ComputerError_Mark{" +
                "uuid='" + uuid + '\'' +
                ", computerError=" + computerError +
                ", computerMark=" + computerMark +
                ", value=" + value +
                '}';
    }
}
