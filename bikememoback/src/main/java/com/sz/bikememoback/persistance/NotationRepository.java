package com.sz.bikememoback.persistance;

import com.sz.bikememoback.models.Notation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotationRepository extends JpaRepository<Notation, Long> {
}
