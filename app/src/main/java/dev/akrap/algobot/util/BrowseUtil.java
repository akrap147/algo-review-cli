package dev.akrap.algobot.util;

public class BrowseUtil {

    public static void openUrl(String url) {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("mac")) {
                new ProcessBuilder("open", url).start();
            } else if (os.contains("win")) {
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
            } else {
                new ProcessBuilder("xdg-open", url).start();
            }
        } catch (Exception e) {
            System.err.println("브라우저 열기 실패: " + e.getMessage());
        }
    }
}