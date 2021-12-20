package org.example.repository;

import org.example.entity.PurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurseRepository extends JpaRepository<PurseEntity, Long> {
}
