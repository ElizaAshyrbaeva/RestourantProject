package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "cheques")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_seq")
    @SequenceGenerator(name = "cheque_seq",allocationSize = 1)
    private Long id;
    private double priceAverage;
    private LocalDate createAt;
    private double grandTotal;

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH,DETACH})
    private Employee employee;

    @ManyToMany( cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private List<MenuItem> menuItems;
        public void  addMenu(MenuItem item){
            if (menuItems==null){
                menuItems=new ArrayList<>();
            }
            menuItems.add(item);
        }
}