package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "call")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "duration")
    private int duration;

    @NonNull
    @Column(name = "user_id")
    private int user_id;

    @NonNull
    @Column(name = "interlocutor")
    private String interlocutor;

    @NonNull
    @Column(name = "created")
    private LocalDateTime created;
}
