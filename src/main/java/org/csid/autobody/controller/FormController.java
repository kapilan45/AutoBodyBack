package org.csid.autobody.controller;

import org.csid.autobody.dto.AnnonceDto;
import org.springframework.web.bind.annotation.PostMapping;

public class FormController extends ApiController {

    AnnonceDto annonceDto = new AnnonceDto();

    @PostMapping("/form")
    public AnnonceDto getForm(){
        return annonceDto;
    }
}
