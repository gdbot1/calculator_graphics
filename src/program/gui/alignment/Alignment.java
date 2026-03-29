package program.gui.alignment;

public enum Alignment {
    TOP(0),
    TOP_RIGHT(1),
    RIGHT(2),
    BOTTOM_RIGHT(3),
    BOTTOM(4),
    BOTTOM_LEFT(5),
    LEFT(6),
    TOP_LEFT(7),
    CENTER(8);

    public final int alignment;

    Alignment(int alignment) {
        this.alignment = alignment;
    }

    public int getAlignmentNumber() {
        return alignment;
    }

    @Override
    public String toString() {
        return name() + "(" + alignment + ")";
    }

    public float[] getAlignmentPosition(float x, float y, float width, float height, float box_width, float box_height, boolean boxIsText) {
        return getAlignmentPosition(x, y, width, height, box_width, box_height, boxIsText, this);
    }

    /**
     * Функция определяет координаты текста в коробке по позиционировании.
     * @param x,y,width,height - параметры коробки.
     * @param box_width,text_height - параметры текста
     * @param alignment - позиционирование текста в коробке.
     * @return координаты текста.
     */
    public static float[] getAlignmentPosition(float x, float y, float width, float height, float box_width, float box_height, boolean boxIsText, Alignment alignment) {
        float center_box_height = boxIsText ? box_height : -box_height, top_box_height = boxIsText ? box_height : 0, bottom_box_height = boxIsText ? 0 : box_height;

        float left_x = x, center_x = x + width / 2 - box_width / 2, right_x = x + width - box_width;
        float top_y = y + top_box_height, center_y = y + height / 2 + center_box_height / 2, bottom_y = y + height - bottom_box_height;

        return switch (alignment) {
            case TOP -> new float[]{center_x, top_y};
            case TOP_RIGHT -> new float[]{right_x, top_y};
            case RIGHT -> new float[]{right_x, center_y};
            case BOTTOM_RIGHT -> new float[]{right_x, bottom_y};
            case BOTTOM -> new float[]{center_x, bottom_y};
            case BOTTOM_LEFT -> new float[]{left_x, bottom_y};
            case LEFT -> new float[]{left_x, center_y};
            case TOP_LEFT -> new float[]{left_x, top_y};
            case CENTER -> new float[]{center_x, center_y};
        };
    }
}