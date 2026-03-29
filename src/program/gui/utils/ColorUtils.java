package program.gui.utils;

import java.awt.*;

public class ColorUtils {
    public static Color lightColorRGB (Color color, int coefficient) {
        return new Color(
                Math.clamp(color.getRed() + coefficient, 0, 255),
                Math.clamp(color.getGreen() + coefficient, 0, 255),
                Math.clamp(color.getBlue() + coefficient, 0, 255)
        );
    }
}
