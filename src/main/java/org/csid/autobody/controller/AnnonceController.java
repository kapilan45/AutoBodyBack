package org.csid.autobody.controller;

import org.csid.autobody.dto.VehiculeDto;
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

    @GetMapping
    public List<VehiculeDto> getAllAnnonces(){
        List<VehiculeDto> vehiculeDtos = new ArrayList<>();
        vehiculeDtos.add(new VehiculeDto((long) 1,"audi", "A3", "4x4", "essence", 100, 2789, 3, "paris", 43, "https://www.automobile-magazine.fr/asset/cms/840x394/171025/config/119825/dsc5638.webp?webp=1"));
        vehiculeDtos.add(new VehiculeDto((long) 1,"bmw", "X5", "citadin", "diesel", 100, 2789, 3, "paris", 43, "https://gcm.moniteurautomobile.be/imgcontrol/c680-d465/clients/moniteur/content/medias/images/test_drives/9000/700/50/002_bmw_x5_phev_0783.jpg"));
        vehiculeDtos.add(new VehiculeDto((long) 1,"ferrari", "GT", "break", "diesel", 100, 2789, 3, "paris", 43, "https://static.autojunk.nl/pictures/2019/0706/083114/ferrari-sf90-stradale_01.jpg"));
        return vehiculeDtos;

        }
}
