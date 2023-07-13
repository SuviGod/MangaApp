package ua.sulima.mangaapp.rest_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.sulima.mangaapp.domain.Manga;
import ua.sulima.mangaapp.dto.manga.MangaToCreateDTO;
import ua.sulima.mangaapp.dto.manga.MangaToShowDTO;
import ua.sulima.mangaapp.service.MangaService;

import java.io.IOException;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mangas")
public class MangaController {
    private final MangaService mangaService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<MangaToShowDTO> getManga(@PathVariable("id") Integer mangaId){
        log.info("Requesting for manga with id -> {}", mangaId);
        Manga manga = mangaService.findById(mangaId);
        MangaToShowDTO mangaToShowDTO = modelMapper.map(manga, MangaToShowDTO.class);
        return ResponseEntity.ok(mangaToShowDTO);
    }
    @PostMapping(value = "/new-multipart", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Manga> createMangaWithMultipartRequest(
            @RequestPart MangaToCreateDTO mangaToCreateDTO,
            @RequestPart MultipartFile previewImageOfManga)
            throws IOException {

        Manga createdManga = mangaService.createMangaWithMultipartRequest(
                mangaToCreateDTO, previewImageOfManga);
        log.info(String.format("Manga with name %d successfully created", createdManga.getId() ));
        URI created_manga_uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdManga.getId())
                .toUri();
        return ResponseEntity.created(created_manga_uri).build();
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Manga> createMangaWithModelAttribute(
            @ModelAttribute MangaToCreateDTO mangaToCreateDTO,
            @RequestParam("image") MultipartFile previewImageOfManga)
            throws IOException{


        return createMangaWithMultipartRequest(mangaToCreateDTO, previewImageOfManga);
    }
}
