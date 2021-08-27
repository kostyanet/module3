package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "traffic")
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "traffic")
    private int traffic;

    @NonNull
    @Column(name = "user_id")
    private int user_id;

    @NonNull
    @Column(name = "created")
    private LocalDateTime created;
}
