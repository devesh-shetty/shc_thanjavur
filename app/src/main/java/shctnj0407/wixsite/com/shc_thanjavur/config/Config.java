package shctnj0407.wixsite.com.shc_thanjavur.config;

/**
 * @author deveshshetty
 */
public class Config {

    public static final String PDF_CHURCH_HISTORY = "church_history.pdf";
    public static final String PDF_MASS_TIMINGS = "mass_timings.pdf";
    public static final int IMAGE_ITEM_MAX_WIDTH = 400;
    public static final int IMAGE_ITEM_MAX_HEIGHT = 400;
    public static final int IMAGE_ITEM_SIZE = (int) Math.ceil(Math.sqrt(Config.IMAGE_ITEM_MAX_WIDTH * Config.IMAGE_ITEM_MAX_HEIGHT));
}
