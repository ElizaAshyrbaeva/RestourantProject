package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stop_list")
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stop_list_seq")
    @SequenceGenerator(name = "stop_list_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    private String reason;
    @OneToOne(mappedBy = "stopList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Menultem menultem;

}