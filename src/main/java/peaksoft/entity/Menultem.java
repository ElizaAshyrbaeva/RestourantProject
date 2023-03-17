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
@Table(name = "menultems")
public class Menultem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menultem_seq")
    @SequenceGenerator(name = "menultem_seq",allocationSize = 1)
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
    @JoinTable(name = "menultems_cheques",
            joinColumns = @JoinColumn(name = "menultem_id"),
            inverseJoinColumns = @JoinColumn(name = "cheques_id"))
    private Set<Cheque> cheques = new LinkedHashSet<>();

    @ManyToOne(cascade = ALL)
    private Subcategory subcategory;
    @OneToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH}, orphanRemoval = true)
    private StopList stopList;

}