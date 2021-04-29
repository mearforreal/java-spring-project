package kz.csse.balling.basketballing.entites;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="t_surfaces")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Surfaces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "surface_type")
    private String surfaceType;
}
