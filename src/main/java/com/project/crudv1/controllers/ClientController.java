package com.project.crudv1.controllers;


import com.project.crudv1.entity.Clients;
import com.project.crudv1.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService theClientService){

        clientService = theClientService;
    }



    @GetMapping("/list")
    public String getAllPages(Model aModel, @RequestParam(required = false) String keyword) {
        List<Clients> theClients;
        int defaultPageSize = 5;

        if (keyword == null) {
            return getOnePage(aModel, 1, defaultPageSize);
        } else {
            theClients = clientService.findByKeyword(keyword);
            aModel.addAttribute("clientss", theClients);
            aModel.addAttribute("keyword", keyword);
            return "client_list";
        }
    }


    @GetMapping("/list/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage,
                             @RequestParam(defaultValue = "10") int pageSize){
        Page<Clients> page = clientService.findPage(currentPage, pageSize);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Clients> countries = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("clientss", countries);
        model.addAttribute("pageSize", pageSize);

        return "client_list";
    }


    @GetMapping("/add")
        public String addClient(Model aModel){

        Clients theClient = new Clients();

            aModel.addAttribute("clients", theClient);

            return "form";

        }
        @PostMapping("/save")
        public String saveClient(@ModelAttribute("clients") Clients theClient){

        clientService.save(theClient);

        return "redirect:/clients/list";
        }

    @PostMapping("/update")
    public String updateClient(@RequestParam("clientId") int theId, Model theModel) {

    Clients theClient = clientService.findById(theId);

        theModel.addAttribute("clients", theClient);

        return "form";
    }

    @PostMapping("/delete")
    public String deleteClient(@RequestParam("clientId") int theId){

        clientService.deleteById(theId);


        return "redirect:/clients/list";
    }


}
