package org.csid.autobody.mapper;


import org.csid.autobody.dto.VehiculeDto;
import org.csid.autobody.entity.Vehicule;
import org.csid.autobody.entity.VehiculeEntity;
import java.util.List;
import java.util.stream.Collectors;

public class VehiculeMapper {

    public static VehiculeDto toVehiculeDto(Vehicule vehicule){
        return VehiculeDto.VehiculeDtoBuilder.create()
                .withId(vehicule.getId())
                .withMarque(vehicule.getMarque())
                .withCategorie(vehicule.getCategorie())
                .withEnergie(vehicule.getEnergie())
                .withKilometrage(vehicule.getKilometrage())
                .withAnnee(vehicule.getAnnee())
                .withStage(vehicule.getStage())
                .withLocalisation(vehicule.getLocalisation())
                .withPrix(vehicule.getPrix())
                .build();
    }

    public static Vehicule toVehicule(VehiculeDto vehicule){
        return Vehicule.VehiculeBuilder.create()
                .withId(vehicule.getId())
                .withMarque(vehicule.getMarque())
                .withCategorie(vehicule.getCategorie())
                .withEnergie(vehicule.getEnergie())
                .withKilometrage(vehicule.getKilometrage())
                .withAnnee(vehicule.getAnnee())
                .withStage(vehicule.getStage())
                .withLocalisation(vehicule.getLocalisation())
                .withPrix(vehicule.getPrix())
                .build();
    }

    public static Vehicule toVehicule(VehiculeEntity vehicule){
        return Vehicule.VehiculeBuilder.create()
                .withId(vehicule.getId())
                .withMarque(vehicule.getMarque())
                .withCategorie(vehicule.getCategorie())
                .withEnergie(vehicule.getEnergie())
                .withKilometrage(vehicule.getKilometrage())
                .withAnnee(vehicule.getAnnee())
                .withStage(vehicule.getStage())
                .withLocalisation(vehicule.getLocalisation())
                .withPrix(vehicule.getPrix())
                .build();
    }

    public static VehiculeEntity toVehicule(Vehicule vehicule){
        return VehiculeEntity.VehiculeEntityBuilder.create()
                .withId(vehicule.getId())
                .withMarque(vehicule.getMarque())
                .withCategorie(vehicule.getCategorie())
                .withEnergie(vehicule.getEnergie())
                .withKilometrage(vehicule.getKilometrage())
                .withAnnee(vehicule.getAnnee())
                .withStage(vehicule.getStage())
                .withLocalisation(vehicule.getLocalisation())
                .withPrix(vehicule.getPrix())
                .build();
    }

    public static List<Vehicule> toVehiculeList(List<VehiculeEntity> vehiculeEntities){
        return vehiculeEntities.stream()
                .map(VehiculeMapper::toVehicule)
                .collect(Collectors.toList());
    }

    public static List<VehiculeDto> toEmployeesDtoList(List<Vehicule> employeeEntities) {
        return employeeEntities.stream()
                .map(VehiculeMapper::toVehiculeDto)
                .collect(Collectors.toList());
    }
}
