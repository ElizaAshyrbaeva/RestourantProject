package peaksoft.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "menu_items")
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuitem_seq")
    @SequenceGenerator(name = "menuitem_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    private Boolean isVegetarian;
    private Boolean inStock;

    @ManyToOne( cascade = {PERSIST,
            MERGE,
            REFRESH,
            DETACH})
    private Restaurant restaurant;

    @ManyToMany(cascade = ALL, mappedBy = "menuItems")
    private List<Cheque> cheques ;

    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private SubCategory subcategory;
    @OneToOne(cascade = {ALL}, orphanRemoval = true,mappedBy = "menuitem")
    private StopList list;
}