package peaksoft.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.LinkedHashSet;
import java.util.Set;
import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "menu_items")

public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuitem_seq")
    @SequenceGenerator(name = "menuitem_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    private boolean isVegetarian;

    @ManyToOne( cascade = {PERSIST,
            MERGE,
            REFRESH,
            DETACH})
    private Restaurant restaurant;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Set<Cheque> cheques = new LinkedHashSet<>();

    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private SubCategory subcategory;
    @OneToOne(cascade = {ALL}, orphanRemoval = true)
    private StopList stopList;

}