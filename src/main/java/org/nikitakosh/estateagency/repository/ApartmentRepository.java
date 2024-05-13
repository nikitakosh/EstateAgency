package org.nikitakosh.estateagency.repository;

import org.nikitakosh.estateagency.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

}
