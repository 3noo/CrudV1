package com.project.crudv1.service;

import com.project.crudv1.entity.Clients;

import com.project.crudv1.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{


private ClientRepository clientRepository;

@Autowired
public ClientServiceImpl (ClientRepository theClientRepository){
    clientRepository = theClientRepository;
}
    @Override
    public List<Clients> findAll() {
        return clientRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Clients findById(int theId) {
        Optional<Clients> result = clientRepository.findById(theId);

        Clients theClient = null;


        if (result.isPresent()) {
            theClient = result.get();
        }
        else {

            throw new RuntimeException("Did not find client id - " + theId);
        }

        return theClient;
    }

    @Override
    public void save(Clients theClient) {

    clientRepository.save(theClient);

    }

    @Override
    public void deleteById(int theId) {

        clientRepository.deleteById(theId);

    }

    @Override
    public List<Clients> findByKeyword(String keyword) {
        return clientRepository.getByKeyword(keyword);
    }

    @Override
    public Page<Clients> findPage(int pageNumber,int pagesize) {
        Pageable pageable = PageRequest.of(pageNumber - 1,pagesize);
        return clientRepository.findAll(pageable);
    }
}
