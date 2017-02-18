package core.browser;

/**
 * This method take OS propertyes and return absolute path for chrome driver for windows and unix system
 * Created by sergey on 4/22/16.
 */

public class ChromeProperties {

    private String osName = System.getProperty("os.name").toLowerCase();
    private String osArch = System.getProperty("os.arch").toLowerCase();

    public String osDetection() {
        if (osName.contains("linux") || osName.contains("nix")) {
            if (osArch.contains("64")) {
                return "resources/chromedriver_x64";
            }
            return "resources/chromedriver";
        } else if (osName.contains("mac") || osName.contains("osX")) {
            return "resources/chromedriver_osX";
        } else {
            return "resources/chromedriver.exe";
        }
    }

}
