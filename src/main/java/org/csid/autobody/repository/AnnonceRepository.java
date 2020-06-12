package org.csid.autobody.repository;

import org.csid.autobody.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<AnnonceEntity, Long> {

    List<AnnonceEntity> findAllByOrderByPublishedDateAsc();

    List<AnnonceEntity> findAllByOrderByPublishedDateDesc();

    List<AnnonceEntity> findAllByOrderByPriceAsc();

    List<AnnonceEntity> findAllByOrderByPriceDesc();

    List<AnnonceEntity> findAllByPriceBetween(double min, double max);

    @Query("select u from AnnonceEntity u where u.user = ?1")
    List<AnnonceEntity> findByUser(Long userId);

    @Query("SELECT u from AnnonceEntity u where u.price > ?1 and u.price < ?2")
    List<AnnonceEntity> findByPriceBetween(int minPrix, int maxPrix);


    @Query("SELECT u from AnnonceEntity u where u.make = ?1 and u.model = ?2 and u.category = ?3" +
            "and u.price BETWEEN ?4 AND ?5 " +
            "and u.torque BETWEEN ?6 AND ?7 " +
            "and u.torqueSinceTheLatestModification BETWEEN ?8 AND ?9 " +
            "and u.fuelEconomy BETWEEN ?10 and ?11 " +
            "and u.mileage BETWEEN ?12 and ?13 " +
            "and u.mileageSince1stModification BETWEEN ?14 and ?15 " +
            "and u.year BETWEEN ?16 AND ?17 a" +
            "and u.fiscalHorsePower BETWEEN ?18 and ?19 " +
            "and u.horsePower BETWEEN ?20 AND ?21 ")
    List<AnnonceEntity> findFilteredWithMakeAndModelAndCategory(MakeEntity makeEntity, ModelEntity modelEntity, CategoryEntity categoryEntity, int minPrice, int maxPrice,
                                                                int minTorque, int maxTorque, int minTorqueSinceModification, int maxTorqueSinceModification,
                                                                int minFuelEconomy, int maxFuelEconomy, Long minMileage, Long maxMileage, int minMileageSinceModification,
                                                                int maxMileageSinceModification, int minYear, int maxYear,
                                                                int minFiscalHorsePower, int maxFiscalHorsePower, int minHorsePower, int maxHorsePower);

    @Query("SELECT u from AnnonceEntity u where u.make = ?1 and u.model = ?2"+
            "and u.price BETWEEN ?3 AND ?4 " +
            "and u.torque BETWEEN ?5 AND ?6 " +
            "and u.torqueSinceTheLatestModification BETWEEN ?7 AND ?8 " +
            "and u.fuelEconomy BETWEEN ?9 and ?10 " +
            "and u.mileage BETWEEN ?11 and ?12 " +
            "and u.mileageSince1stModification BETWEEN ?13 and ?14 " +
            "and u.year BETWEEN ?15 AND ?16 a" +
            "and u.fiscalHorsePower BETWEEN ?17 and ?18 " +
            "and u.horsePower BETWEEN ?19 AND ?20 ")
    List<AnnonceEntity> findFilteredWithMakeAndModel(MakeEntity makeEntity, ModelEntity modelEntity, int minPrice, int maxPrice,
                                                     int minTorque, int maxTorque, int minTorqueSinceModification, int maxTorqueSinceModification,
                                                     int minFuelEconomy, int maxFuelEconomy, Long minMileage, Long maxMileage, int minMileageSinceModification,
                                                     int maxMileageSinceModification, int minYear, int maxYear,
                                                     int minFiscalHorsePower, int maxFiscalHorsePower, int minHorsePower, int maxHorsePower);

    @Query("SELECT u from AnnonceEntity u where u.make = ?1" +
            "and u.horsePower BETWEEN ?2 AND ?3" +
            "and u.price BETWEEN ?4 AND ?5 " +
            "and u.torque BETWEEN ?6 AND ?7 " +
            "and u.torqueSinceTheLatestModification BETWEEN ?8 AND ?9 " +
            "and u.fuelEconomy BETWEEN ?10 and ?11 " +
            "and u.mileage BETWEEN ?12 and ?13 " +
            "and u.mileageSince1stModification BETWEEN ?14 and ?15 " +
            "and u.yearBETWEEN ?16 AND ?17 a" +
            "and u.fiscalHorsePower BETWEEN ?18 and ?19 ")
    List<AnnonceEntity> findFilteredWithMake(MakeEntity makeEntity, int minHorsePower, int maxHorsePower, int minPrice, int maxPrice,
                                             int minTorque, int maxTorque, int minTorqueSinceModification, int maxTorqueSinceModification,
                                             int minFuelEconomy, int maxFuelEconomy, Long minMileage, Long maxMileage,
                                             int minMileageSinceModification, int maxMileageSinceModification, int minYear, int maxYear,
                                             int minFiscalHorsePower, int maxFiscalHorsePower);

    @Query("SELECT u from AnnonceEntity u " +
            "WHERE u.horsePower BETWEEN ?1 AND ?2" +
            "and u.price BETWEEN ?3 AND ?4 " +
            "and u.torque BETWEEN ?5 AND ?6 " +
            "and u.torqueSinceTheLatestModification BETWEEN ?7 AND ?8 " +
            "and u.fuelEconomy BETWEEN ?9 and ?10 " +
            "and u.mileage BETWEEN ?11 and ?12 " +
            "and u.mileageSince1stModification BETWEEN ?13 and ?14 " +
            "and u.year BETWEEN ?15 AND ?16 a" +
            "and u.fiscalHorsePower BETWEEN ?17 and ?18 "
            )
    List<AnnonceEntity> findFiltered(int minHorsePower, int maxHorsePower, int minPrice, int maxPrice,
                                     int minTorque, int maxTorque, int minTorqueSinceModification, int maxTorqueSinceModification,
                                     int minFuelEconomy, int maxFuelEconomy, Long minMileage, Long maxMileage, int minMileageSinceModification,
                                     int maxMileageSinceModification, int minYear, int maxYear,
                                     int minFiscalHorsePower, int maxFiscalHorsePower);
}
