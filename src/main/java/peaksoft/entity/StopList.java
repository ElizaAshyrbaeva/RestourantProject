package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_seq")
    @SequenceGenerator(name = "list_seq")
    private Long id;
    private String reason;
    private LocalDate date;
    @OneToOne(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private MenuItem menuitem;

    public StopList(LocalDate date, String reason) {
        this.date = date;
        this.reason = reason;
    }
}