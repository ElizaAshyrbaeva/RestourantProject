package peaksoft.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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

    @ManyToOne( cascade = {PERSIST,
            MERGE,
            REFRESH,
            DETACH})
    private Restaurant restaurant;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private List<Cheque> cheques ;

    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private SubCategory subcategory;
    @OneToOne(cascade = {ALL}, orphanRemoval = true)
    private StopList list;
    public  void addCheck(Cheque cheque){
        if (cheques==null){
            cheques=new ArrayList<>();
        }
        cheques.add(cheque);
    }

    public MenuItem(String name, String image, int price, String description, boolean isVegetarian, Restaurant restaurant) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.restaurant = restaurant;
    }
}