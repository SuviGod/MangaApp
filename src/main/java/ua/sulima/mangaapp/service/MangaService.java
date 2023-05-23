package ua.sulima.mangaapp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ua.sulima.mangaapp.domain.Creator;
import ua.sulima.mangaapp.domain.Manga;
import ua.sulima.mangaapp.dto.manga.MangaToCreateDTO;
import ua.sulima.mangaapp.repository.CreatorRepository;
import ua.sulima.mangaapp.repository.MangaRepository;
import ua.sulima.mangaapp.user.UserRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MangaService {
    private final UploadService uploadService;

    private final MangaRepository mangaRepository;
    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<Manga> findAll(Pageable pageable){
        return mangaRepository.findAll(pageable);
    }

    public Manga updateManga(Manga mangaToUpdate){

        return mangaRepository.save(mangaToUpdate);
    }

    @Transactional(readOnly = true)
    public Manga findById(Integer id){
        var maybeCreator = mangaRepository.findById(id);
        if (maybeCreator.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return maybeCreator.get();
    }

    @Transactional(readOnly = true)
    public Page<Manga> findAllByAuthorOrArtist(Creator creator, Pageable pageable){
        return mangaRepository.findAllByAuthorOrArtist(creator, pageable);
    }

    public Manga createManga(MangaToCreateDTO mangaToCreateDTO,
                             MultipartFile previewImageOfManga) throws IOException {

        var mangaToCreate = modelMapper.map(mangaToCreateDTO, Manga.class);
        enrichNewManga(mangaToCreateDTO, mangaToCreate);
        Manga createdManga = mangaRepository.save(mangaToCreate);
        Path createdImagePath = uploadService.uploadMangaPreviewImage(
                previewImageOfManga,
                createdManga);
        createdManga.setPreviewImagePath(createdImagePath.toString());
        return createdManga;
    }

    private void enrichNewManga(MangaToCreateDTO mangaToCreateDTO,
                                Manga mangaToCreate){
        enrichNewMangaByReferences(mangaToCreateDTO, mangaToCreate);
        enrichNewMangaDatetimeFields(mangaToCreate);
        mangaToCreate.setPreviewImagePath("");
        mangaToCreate.setIsApproved(false);
    }
    private void enrichNewMangaByReferences(MangaToCreateDTO mangaToCreateDTO,
                                           Manga mangaToCreate){
        mangaToCreate.setAuthor(
                creatorRepository.getReferenceById(
                        mangaToCreateDTO.getAuthorId()));
        mangaToCreate.setArtist(
                creatorRepository.getReferenceById(
                        mangaToCreateDTO.getArtistId()));
        mangaToCreate.setTranslator(
                userRepository.getReferenceById(
                        mangaToCreateDTO.getTranslatorId()));
    }

    private void enrichNewMangaDatetimeFields(Manga mangaToCreate){
        mangaToCreate.setAddDatetime(LocalDateTime.now());
        mangaToCreate.setUpdateDatetime(LocalDateTime.now());
    }
}
