package ua.sulima.mangaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ua.sulima.mangaapp.domain.Creator;
import ua.sulima.mangaapp.repository.CreatorRepository;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final CreatorRepository creatorRepository;

    public Creator updateCreator(Creator creatorToUpdate){
        return creatorRepository.save(creatorToUpdate);
    }

    public Creator findById(Integer id){
        var maybeCreator = creatorRepository.findById(id);
        if (maybeCreator.isEmpty()){
//            throw new IllegalStateException();
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return maybeCreator.get();
    }
}
