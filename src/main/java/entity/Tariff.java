package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "minute_cost")
    private float minute_cost;

    @NonNull
    @Column(name = "mb_cost")
    private float mb_cost;

    @NonNull
    @Column(name = "sms_cost")
    private float sms_cost;
}
