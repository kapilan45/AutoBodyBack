package org.csid.autobody.controller;

import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.services.AnnonceService;
import org.csid.autobody.specifications.AnnoncesSpecificationsBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping
    public List<AnnonceDto> getAllRecent() {
        return annonceService.getAllRecent();
    }

    @GetMapping("/user")
    public List<AnnonceDto> getAllByUser() {
        return annonceService.getAllByUser();
    }

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto) {
        annonceService.saveAnnonce(annonceDto);
    }

    @GetMapping("/basicfilter")
    public List<AnnonceDto> basicFilter(@RequestParam("basicFilter") String filter) {
        return annonceService.getBasicFiltredAnnonce(filter);
    }

    @PutMapping("/delete")
    public List<AnnonceDto> deleteAnnonce(@RequestBody AnnonceDto annonceDto) {
        return annonceService.deleteAnnonce(annonceDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filtre")
    @ResponseBody
    public List<AnnonceDto> getAnnonceFiltered(@RequestParam(value = "search") String search) {
        AnnoncesSpecificationsBuilder builder = new AnnoncesSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        builder = annonceService.mapAnnonceObjects(builder);
        Specification<AnnonceEntity> spec = builder.build();
        List<AnnonceEntity> all = annonceService.getAnnonceFiltered(spec);
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }
}
