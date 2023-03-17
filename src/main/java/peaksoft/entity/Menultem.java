package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "minutemen")
public class Menultem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menultem_seq")
    @SequenceGenerator(name = "menultem_seq")
    private Long id;

}