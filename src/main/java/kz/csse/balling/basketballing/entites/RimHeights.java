package kz.csse.balling.basketballing.entites;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_rim_height")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RimHeights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rim_height")
    private String rimHeight;

}
