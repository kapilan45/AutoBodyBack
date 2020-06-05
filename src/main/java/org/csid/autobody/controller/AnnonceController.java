package org.csid.autobody.controller;

import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.MakeDto;
import org.csid.autobody.dto.ModelDto;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.UserEntity;
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
    public List<AnnonceDto> getAllByPublishedDate(){
        return annonceService.getAllByPublishedDate();
    }

  /* TODO

    @GetMapping("/:user")
    public List<AnnonceDto> getAllByUser(UserDto user) {
        return annonceService.getAllByUser(user);
    }
*/

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto){
        this.annonceService.saveAnnonce(annonceDto);
    }


    @GetMapping("/makes")
    public MakeDto getAllMake(){
        // TODO return toutes markes présents dans base de onnés
        return  null;
    }

    @GetMapping("/models")
    public MakeDto getModel(@RequestBody MakeDto make){
        // TODO return toutes modele de la marque 'make' présent dans la body
        return  null;
    }

    @GetMapping("/category")
    public MakeDto getCategory(@RequestBody ModelDto model){
        // TODO return toutes modele de la modeèle 'model' présent dans la body
        return  null;
    }

}
