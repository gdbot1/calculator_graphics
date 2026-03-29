package program.gui.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class ClipBoardUtils {
    /**
     * Функция копирует текст в клип-борд.
     * @param text - копируемый текст.
     */
    public static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    /**
     * Функция получает текст из клип-борда.
     * @return строка со скопированным ранее текстом.
     */
    public static String getFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        }
        catch (Exception _) {}

        return "";
    }
}
