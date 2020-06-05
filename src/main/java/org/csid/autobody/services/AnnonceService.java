package org.csid.autobody.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.*;
import org.csid.autobody.repository.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnnonceService {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";

    private final AnnonceRepository annonceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MakeRepository makeRepository;

    public AnnonceService(AnnonceRepository annonceRepository, CategoryRepository categoryRepository, UserRepository userRepository, MakeRepository makeRepository) {
        this.annonceRepository = annonceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.makeRepository = makeRepository;
    }


    public void saveAnnonce(AnnonceDto annonceDto, String userToken) {

        AnnonceEntity annonce = DtoConverter.map(annonceDto, AnnonceEntity.class);
        CategoryEntity categoryEntity = categoryRepository.findCategoryByName(annonceDto.getCategory());
        MakeEntity makeEntity = makeRepository.findById(annonceDto.getMake()).orElse(null);

        // Username est récupéré avec UserService.getUserNameWithToken(userToken)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        UserEntity u = userRepository.findByUsername(username);

        // TO DO ANONYMOUS
        if (u == null)
            u = userRepository.getOne(2L);


        //AnnonceEntity annonce = new AnnonceEntity();
        annonce.setCategory(categoryEntity);
        annonce.setUser(u);
        annonce.setMake(makeEntity);
        this.annonceRepository.save(annonce);
    }

    public List<AnnonceDto> getAll() {
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllByPublishedDate(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPublishedDateAsc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }


    public List<AnnonceDto> getAllByUser(UserDto userDto) {
        UserEntity user = DtoConverter.map(userDto, UserEntity.class);
        List<AnnonceEntity> all = annonceRepository.findByUser(user.getId());
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    /*
    public List<AnnonceDto> getAllByFilter(String filter){
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    } */
}
