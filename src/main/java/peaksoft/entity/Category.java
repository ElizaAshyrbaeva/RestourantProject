package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",allocationSize = 1)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subcategory subcategory;

}