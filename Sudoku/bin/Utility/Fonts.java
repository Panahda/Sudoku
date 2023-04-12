package Sudoku.bin.Utility;

import java.awt.Font;
import java.io.InputStream;

public class Fonts {

    //new font location
    private static final String FONT_PATH = "fonts/Sudoku.ttf";

    public static Font loadFont(float size) {

        //Test if able to import Font else use SERIF
        try (InputStream is = Fonts.class.getResourceAsStream(FONT_PATH)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (Exception e) {
            System.err.println("Failed to load font: " + e.getMessage());
            return new Font("Serif", Font.PLAIN, (int)size);
        }
    }
}