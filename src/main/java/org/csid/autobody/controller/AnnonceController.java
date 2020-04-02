package org.csid.autobody.controller;

import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.services.AnnonceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping
    public List<AnnonceDto> getAllAnnonces(){
        return annonceService.getAll();
    }

    @PostMapping
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto){
        this.annonceService.saveAnnonce(annonceDto);
    }
}
