package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceStatistic {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "profit")
    private double profit;
}
