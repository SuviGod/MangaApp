package ua.sulima.mangaapp.service.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.sulima.mangaapp.domain.Manga;
import ua.sulima.mangaapp.exception.rest.CustomNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ua.sulima.mangaapp.service.storage.Constants.*;


@Slf4j
@Getter
@Service
public class FileStorageServiceImpl implements StorageService {
//    public static String CORE_UPLOAD_DIRECTORY = Constants.CORE_UPLOAD_DIRECTORY;
//    public static String MANGAS_DIRECTORY = Constants.MANGAS_DIRECTORY;

//    FileStorageServiceImpl() throws IOException {
//
//        String rootPath = Thread.currentThread()
//                .getContextClassLoader()
//                .getResource("")
//                .getPath();
//
//        String localSavingPropertiesPath = rootPath + "local_saving.properties";
//        Properties localSavingProperties = new Properties();
//        localSavingProperties.load(new FileInputStream(localSavingPropertiesPath));
//        CORE_UPLOAD_DIRECTORY = localSavingProperties.getProperty(PropertiesNames.CORE_UPLOAD_DIRECTORY);
//        MANGAS_DIRECTORY =
//                CORE_UPLOAD_DIRECTORY + "\\uploads\\mangas";
//    }

    @Override
    public Path uploadMangaPreviewImage(MultipartFile previewImageOfManga, Manga manga) {
        String saveImageDirectory =
                MANGAS_DIRECTORY +
                "\\" +
                manga.getId();

        Path saveImageDirectoryPath = Paths.get(saveImageDirectory);
        try{
            if(!Files.exists(saveImageDirectoryPath)){
                Files.createDirectory(saveImageDirectoryPath);
            }
            Path imagePath = Paths.get(saveImageDirectory + "\\"
                    + MANGA_PREVIEW_IMAGE_FILENAME);
            if(!Files.exists(saveImageDirectoryPath)){
                Files.createFile(imagePath);
            }
            return Path.of(CORE_UPLOAD_DIRECTORY)
                    .relativize(Files.write(imagePath, previewImageOfManga.getBytes()));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public byte[] getFileBytes(String uri){
        var previewImagePath = Paths.get(CORE_UPLOAD_DIRECTORY
                + uri.replace("/", "\\"));
        if(!Files.exists(previewImagePath)){
            throw new CustomNotFoundException(
                    String.format("File with path %s wasn't found", previewImagePath));
        }

        try {
            return Files.readAllBytes(previewImagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
