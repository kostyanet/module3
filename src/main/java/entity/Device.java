package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "model")
    private String model;

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
