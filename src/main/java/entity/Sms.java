package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sms")
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "text")
    private String text;

    @NonNull
    @Column(name = "user_id")
    private int user_id;

    @NonNull
    @Column(name = "interlocutor")
    private String interlocutor;

    @NonNull
    @Column(name = "created")
    private LocalDateTime created;

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user_id=" + user_id +
                ", interlocutor='" + interlocutor + '\'' +
                ", created=" + created +
                "}\n";
    }
}
