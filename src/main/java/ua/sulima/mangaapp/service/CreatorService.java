package ua.sulima.mangaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.sulima.mangaapp.domain.Creator;
import ua.sulima.mangaapp.exception.rest.CustomNotFoundException;
import ua.sulima.mangaapp.repository.CreatorRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatorService {

    private final CreatorRepository creatorRepository;

    public Creator updateCreator(Creator creatorToUpdate){
        return creatorRepository.save(creatorToUpdate);
    }

    @Transactional(readOnly = true)
    public Creator findById(Integer id){
        var maybeCreator = creatorRepository.findById(id);
        if (maybeCreator.isEmpty()){
            throw new CustomNotFoundException("Creator is not found");
        }

        return maybeCreator.get();
    }
}
