package kz.csse.balling.basketballing.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_courts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name ="coordinates")
    private String coordinates;

    @Column(name ="hasLigth")
    private boolean hasLight;

    @Column(name ="pickupGame")
    private boolean pickUpGame;

    @Column(name ="hoopsNum")
    private int hoopsNum;

    @Column(name ="courtNum")
    private int courtNum;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private CourtTypes courtType;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private RimHeights rimHeight;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Surfaces surface;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Regions region;


}
