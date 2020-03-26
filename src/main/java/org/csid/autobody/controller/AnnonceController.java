package org.csid.autobody.controller;

import org.csid.autobody.dto.VehiculeDto;
import org.csid.autobody.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("annonces")
public class AnnonceController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping
    public List<VehiculeDto> getAllAnnonces(){
        return vehiculeService.getAll();
    }
}
