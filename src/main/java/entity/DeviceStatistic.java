package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceStatistic {
    @Id
    @Column(name = "model")
    private String model;

    @Column(name = "usage_count")
    private int usageCount;
}
