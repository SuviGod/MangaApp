package ua.sulima.mangaapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.sulima.mangaapp.domain.Manga;
import ua.sulima.mangaapp.dto.manga.MangaToCreateDTO;
import ua.sulima.mangaapp.dto.manga.MangaToShowDTO;
import ua.sulima.mangaapp.service.MangaService;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mangas")
public class MvcMangaController {
    private final MangaService mangaService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String showAllMangas(Model model, Pageable pageable){
        Page<Manga> pageOfMangas = mangaService.findAll(pageable);
        model.addAttribute("pageOfMangas", pageOfMangas);
        return "manga/show_all";
    }

    @GetMapping(value = "/{id}")
    public String showMangaById(@PathVariable("id") Integer id, Model model){
        log.info("Requesting for manga with id -> {}", id);
        Manga manga = mangaService.findById(id);
        MangaToShowDTO mangaToShowDTO = modelMapper.map(manga, MangaToShowDTO.class);

        model.addAttribute("manga", manga);
        return "manga/show";
    }

    @GetMapping("/new")
    public String createMangaForm(Model model){
        model.addAttribute("new_manga", new MangaToCreateDTO());
        return "manga/creation_form";
    }

    @PostMapping("/new")
    public String createManga(
            @ModelAttribute MangaToCreateDTO mangaToCreateDTO,
            @RequestParam("image") MultipartFile previewImageOfManga)
            throws IOException {
        Manga createdManga = mangaService.createMangaWithMultipartRequest(
                mangaToCreateDTO, previewImageOfManga);
        log.info(String.format("Manga with name %d successfully created", createdManga.getId() ));
        return "redirect:/mangas/" + createdManga.getId();
    }


    @GetMapping("/update")
    public String updateMangaForm(Model model){
        model.addAttribute("manga", new Manga());
        return "manga/update_form";
    }



    @PostMapping("/update")
    public String updateManga(@ModelAttribute Manga mangaToUpdate) {
        mangaService.updateManga(mangaToUpdate);
        return "redirect:/index";
    }
    @GetMapping("/oleh")
    public String oleh() throws IOException {
        int a = 5;
        if(a == 5){
            throw new IOException();
        }
        return "index";
    }
}
