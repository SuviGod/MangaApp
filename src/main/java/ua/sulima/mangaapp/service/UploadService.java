package ua.sulima.mangaapp.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.sulima.mangaapp.domain.Manga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Getter
@Service
public class UploadService {
    public static String CORE_UPLOAD_DIRECTORY =
            "D:\\Program Files\\Projects\\MangaAppResources";
    public static String MANGAS_DIRECTORY =
            CORE_UPLOAD_DIRECTORY + "\\uploads\\mangas";
    public static String STATIC_RESOURCES_DIRECTORY =
            CORE_UPLOAD_DIRECTORY + "\\src\\main\\resources\\static";

    public Path uploadMangaPreviewImage(MultipartFile previewImageOfManga, Manga manga)
            throws IOException {
        String saveImageDirectory =
                MANGAS_DIRECTORY +
                "\\" +
                manga.getId();
//                "\\" +
//                "previewImage.jpg";


        Path saveImageDirectoryPath = Paths.get(saveImageDirectory);
        if(!Files.exists(saveImageDirectoryPath)){
            Files.createDirectory(saveImageDirectoryPath);
        }
        Path imagePath = Paths.get(saveImageDirectory + "\\" + "previewImage.jpg");
        if(!Files.exists(saveImageDirectoryPath)){
            Files.createFile(imagePath);
        }
        return Path.of(CORE_UPLOAD_DIRECTORY)
                .relativize(Files.write(imagePath, previewImageOfManga.getBytes()));
    }
}
