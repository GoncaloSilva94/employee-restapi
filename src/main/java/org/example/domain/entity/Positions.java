package org.example.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "positions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Positions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name  = "id")
    private Integer id;

    @Column(name = "description", length = 128)
    private String description;

    public Positions(String description){
        this.description = description;
    }



}
