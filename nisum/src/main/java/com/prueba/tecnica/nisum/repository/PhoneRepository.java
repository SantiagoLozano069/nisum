package com.prueba.tecnica.nisum.repository;

import com.prueba.tecnica.nisum.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {
}
