package com.project.crudv1.repo;

import com.project.crudv1.entity.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Clients, Integer> {

    public List<Clients> findAllByOrderByLastNameAsc();

    @Query(value = "select c from Clients c where "+"c.firstName LIKE %?1% or c.lastName like %?1%")
    List<Clients> getByKeyword (String keyword);
}
