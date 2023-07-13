package ua.sulima.mangaapp.service.storage;

import org.springframework.web.multipart.MultipartFile;
import ua.sulima.mangaapp.domain.Manga;

import java.nio.file.Path;

public interface StorageService {
    Path uploadMangaPreviewImage(MultipartFile previewImageOfManga, Manga manga);

    byte[] getFileBytes(String uri);
}
