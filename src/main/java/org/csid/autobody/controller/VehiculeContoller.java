package org.csid.autobody.controller;

import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonce")
@CrossOrigin(origins = {"http://localhost:4200"})
public class VehiculeContoller {

    private final VehiculeService vehiculeService;

    @Autowired
    public VehiculeContoller(VehiculeService vehiculeService){
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    public List<AnnonceDto> getAllVehicules(){
        return vehiculeService.getAll();
    }



    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public AnnonceDto getForm(Model model){
        model.addAttribute("vehicule", new AnnonceDto());

        return new AnnonceDto();
    }

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto vehiculeDto){
        System.out.println(vehiculeDto.getPrix());
    }


   /* @GetMapping("{id}")
    public VehiculeDto getEmployee(@PathVariable Long id) {
        return VehiculeMapper.toVehiculeDto(vehiculeService.get(id));
    }

    @PostMapping
    public ResponseEntity<VehiculeDto> createVehicule(@RequestBody VehiculeDto vehiculeDto) throws URISyntaxException {

        if(vehiculeDto.getId() != null) {
            throw new IllegalArgumentException("Employee id should not be populated when creating and employee");
        }

        Vehicule newVehicule = vehiculeService.create(VehiculeMapper.toVehicule(vehiculeDto));

        URI uri = new URI(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}")
                .buildAndExpand(newVehicule.getId())
                .toUri().getPath()
        );

        return ResponseEntity.created(uri).body(VehiculeMapper.toVehiculeDto(newVehicule));
    }


/*    public ResponseEntity<List<VehiculeDto>> createEmployees(@RequestBody List<VehiculeDto> vehiculesDto){

        vehiculesDto.stream().forEach(vehiculeDto -> {
            try {
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        return new ResponseEntity<List<VehiculeDto>>(vehiculesDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public VehiculeDto updateVehicule(@PathVariable Long id, @RequestBody VehiculeDto vehiculeDto) {
        if(vehiculeDto.getId() == null) {
            throw new IllegalArgumentException("Employee id should be populated for HTTP PUT method: you cannot predict its id");
        }
        if(!id.equals(vehiculeDto.getId())) {
            throw new IdMismatchException("Path id and payload id do not match");
        }

        Vehicule updatedVehicule = vehiculeService.update(VehiculeMapper.toVehicule(vehiculeDto));
        return VehiculeMapper.toVehiculeDto(updatedVehicule);
    }

    @PatchMapping("{id}")
    public VehiculeDto partialUpdateEmployee(@PathVariable Long id, @RequestBody VehiculeDto vehiculeDto) {
        if(vehiculeDto.getId() == null) {
            throw new IllegalArgumentException("Vehicule id should be populated for HTTP PUT method: you cannot predict its id");
        }
        if(!id.equals(vehiculeDto.getId())) {
            throw new IdMismatchException("Path id and payload id do not match");
        }

        Vehicule updatedVehicule = vehiculeService.partialUpdate(VehiculeMapper.toVehicule(vehiculeDto));
        return VehiculeMapper.toVehiculeDto(updatedVehicule);
    }



    @DeleteMapping("{id}")
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeService.delete(id);
    }

    */
}
