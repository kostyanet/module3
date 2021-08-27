package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "first_name")
    private String first_name;

    @NonNull
    @Column(name = "last_name")
    private String last_name;

    @NonNull
    @Column(name = "tariff_id")
    private int tariff_id;

    @NonNull
    @Column(name = "device_id")
    private int device_id;
}
