package program.gui.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FontUtils {
    public static int getTextWidth(String text, Font font) {
        Graphics graphics = createGraphics();

        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        int stringWidth = fontMetrics.stringWidth(text);

        graphics.dispose();

        return stringWidth;
    }

    private static Graphics createGraphics() {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
    }
}
