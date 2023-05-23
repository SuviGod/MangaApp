package ua.sulima.mangaapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sulima.mangaapp.domain.Creator;
import ua.sulima.mangaapp.domain.Manga;
import ua.sulima.mangaapp.service.CreatorService;
import ua.sulima.mangaapp.service.MangaService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/creators")
public class CreatorController {
    private final CreatorService creatorService;

    private final MangaService mangaService;

    @GetMapping(value = "/{id}")
    public String showCreatorById(@PathVariable("id") Integer id,
                                  Pageable pageable, Model model){
        log.info("Requesting for creator with id -> {}", id);
        Creator creator = creatorService.findById(id);
        model.addAttribute("creator", creator);

        Page<Manga> pageOfRelatedMangas =
                mangaService.findAllByAuthorOrArtist(creator, pageable);
        model.addAttribute("pageOfMangas", pageOfRelatedMangas);
        return "creator/show";
    }

    @GetMapping("/new")
    public String createCreatorForm(Model model){
        model.addAttribute("new_creator", new Creator());
        return "creator/creation_form";
    }

    @PostMapping("/new")
    public String createCreator(@ModelAttribute Creator creatorToUpdate){
        return updateCreator(creatorToUpdate);
    }


    @GetMapping("/update")
    public String updateCreatorForm(Model model){
        model.addAttribute("creator", new Creator());
        return "creator/update_form";
    }

    @PostMapping("/update")
    public String updateCreator(@ModelAttribute Creator creatorToUpdate){
        Creator updatedCreator = creatorService.updateCreator(creatorToUpdate);
        return "redirect:/creators/" + updatedCreator.getId();
    }


}
