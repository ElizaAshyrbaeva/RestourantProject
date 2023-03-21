package peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subcategories")
@Builder
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcategory_seq")
    @SequenceGenerator(name = "subcategory_seq", allocationSize = 1)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subcategory", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH},orphanRemoval = true)
    private List<MenuItem> menuItem;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Category categories;

}