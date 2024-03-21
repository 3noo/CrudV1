package com.project.crudv1.service;

import com.project.crudv1.entity.Clients;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {



    List<Clients> findAll();

    Clients findById(int theId);

    void save(Clients theEmployee);

    void deleteById(int theId);

    public List<Clients>findByKeyword(String keyword);

    public Page<Clients>findPage(int pageNumber,int pagesize);

}
