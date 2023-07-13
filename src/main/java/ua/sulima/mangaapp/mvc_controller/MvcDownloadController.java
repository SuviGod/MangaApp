package ua.sulima.mangaapp.mvc_controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.sulima.mangaapp.service.storage.StorageService;

import java.io.IOException;

@Controller
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class MvcDownloadController {
    private final StorageService storageService;
//    @GetMapping("/mangas/{}/")
//    @ResponseBody
//    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("jpg") boolean jpg) {
//        MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
//        InputStream in = jpg ?
//                getClass().getResourceAsStream("/com/baeldung/produceimage/image.jpg") :
//                getClass().getResourceAsStream("/com/baeldung/produceimage/image.png");
//        return ResponseEntity.ok()
//                .contentType(contentType)
//                .body(new InputStreamResource(in));
//    }

//    @GetMapping(
//            value = "/mangas/{manga_id}/previewImage.jpg",
//            produces = MediaType.IMAGE_JPEG_VALUE
//    )
//    public @ResponseBody byte[] getPreviewMangaImage(HttpServletRequest httpServletRequest) throws IOException {
//        InputStream in = getClass()
//                .getResourceAsStream(FileStorageServiceImpl.CORE_UPLOAD_DIRECTORY
//                        + httpServletRequest.getRequestURI().replace("/", "\\"));
//        if(in == null){
//            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
//        }
//        return IOUtils.toByteArray(in);
//    }

    @GetMapping(
            value = "/mangas/{manga_id}/previewImage.jpg",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getPreviewMangaImage(HttpServletRequest httpServletRequest) throws IOException {
        return storageService.getFileBytes(httpServletRequest.getRequestURI());
    }
}
