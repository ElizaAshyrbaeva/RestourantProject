package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_seq")
    @SequenceGenerator(name = "employee_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int experience;


    @ManyToOne(cascade = {PERSIST,MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Restaurant restaurant;

    @OneToMany(mappedBy = "employee", cascade = {PERSIST, MERGE,REFRESH,DETACH}, orphanRemoval = true)
    private Set<Cheque> cheques = new LinkedHashSet<>();

}