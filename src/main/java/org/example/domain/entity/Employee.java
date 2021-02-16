package org.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name", length = 128)  //Does not accept empty values
    private String name;

    @NotNull
    @Column(name = "birthdate")         //Does not accept empty values
    private LocalDate birthdate;

    @NotNull
    @Column(name = "address")           //Does not accept empty values
    private String address;

    @ManyToOne
    @JoinColumn(name = "status_id")   //Foreign key - Status id
    private Status status;

    @ManyToOne
    @JoinColumn(name = "position_id")  //Foreign key - Positions id
    private Positions positions;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;






}
