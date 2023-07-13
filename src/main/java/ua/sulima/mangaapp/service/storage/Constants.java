package ua.sulima.mangaapp.service.storage;

public class Constants {

    public static final String CORE_UPLOAD_DIRECTORY
            = Configurations.getInstance().getProperty(PropertiesNames.CORE_UPLOAD_DIRECTORY);;
    public static final String MANGAS_DIRECTORY = CORE_UPLOAD_DIRECTORY + "\\uploads\\mangas";

    public static final String MANGA_PREVIEW_IMAGE_FILENAME = "previewImage.jpg";

    public static final String LOCAL_SAVING_PROPERTIES_PATH = "local_saving.properties";

}
