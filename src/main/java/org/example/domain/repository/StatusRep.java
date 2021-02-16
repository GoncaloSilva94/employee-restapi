package org.example.domain.repository;

import org.example.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRep extends JpaRepository<Status, Integer> {
}
