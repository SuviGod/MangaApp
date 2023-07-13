package ua.sulima.mangaapp.rest_controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.sulima.mangaapp.domain.Creator;
import ua.sulima.mangaapp.service.CreatorService;
import ua.sulima.mangaapp.service.MangaService;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/creators")
public class CreatorController {

    private final CreatorService creatorService;

    private final MangaService mangaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Creator> showCreatorById(@PathVariable("id") Integer id){
        log.info("Requesting for creator with id -> {}", id);
        Creator creator = creatorService.findById(id);
        return ResponseEntity.ok(creator);
    }

    @PostMapping("/new")
    public ResponseEntity<Creator> createCreator(@RequestBody Creator creatorToUpdate){
        return updateCreator(creatorToUpdate);
    }


    @PostMapping("/update")
    public ResponseEntity<Creator> updateCreator(@RequestBody Creator creatorToUpdate){
        Creator updatedCreator = creatorService.updateCreator(creatorToUpdate);
        URI newCreatorLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedCreator.getId())
                .toUri();
        return ResponseEntity.created(newCreatorLocation).build();
    }
}
