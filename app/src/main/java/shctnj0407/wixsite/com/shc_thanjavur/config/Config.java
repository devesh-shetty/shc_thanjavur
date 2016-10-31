package shctnj0407.wixsite.com.shc_thanjavur.config;

/**
 * @author deveshshetty
 */
public final class Config {

    public static final int IMAGE_ITEM_MAX_WIDTH = 400;
    public static final int IMAGE_ITEM_MAX_HEIGHT = 400;
    public static final int IMAGE_ITEM_SIZE = (int) Math.ceil(Math.sqrt(Config.IMAGE_ITEM_MAX_WIDTH * Config.IMAGE_ITEM_MAX_HEIGHT));


    //the url of the gallery web service
    public static final String GALLERY_WEB_SERVICE_URL = "galleryService.php";
    //sub-directory of images
    public static final String GALLERY_IMAGE_SUB_DIRECTORY = "assets/gallery/";

}
