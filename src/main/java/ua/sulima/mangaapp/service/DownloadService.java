package ua.sulima.mangaapp.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DownloadService {

    public byte[] getImageBytes(String uri) throws IOException {
        var previewImagePath = Paths.get(UploadService.CORE_UPLOAD_DIRECTORY
                + uri.replace("/", "\\"));
        if(!Files.exists(previewImagePath)){
            throw new FileNotFoundException();
        }
        return Files.readAllBytes(previewImagePath);
    }

}
