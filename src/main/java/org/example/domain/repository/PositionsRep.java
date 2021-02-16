package org.example.domain.repository;

import org.example.domain.entity.Positions;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PositionsRep extends JpaRepository<Positions, Integer> {


}
