package program.gui.utils;

import program.gui.element.elements.textField.ITextField;

import java.awt.event.KeyEvent;

public class InputUtils {
    /**
     * Функция реализует логику движения курсора текстового поля вправо.
     * @param textField - текстовое поле.
     * @param ctrl - состояние клавиши CONTROL.
     * @param shift - состояние клавиши SHIFT.
     */
    public static void moveInRight (ITextField textField, boolean ctrl, boolean shift) {
        //Получение текстового курсора (состоящего из 2 курсоров).
        int[] cursor = textField.getCursor();

        //Получение курсора.
        int from = cursor[0], to = cursor[1];

        //Если 2 курсора вместе.
        boolean together = from == to;

        //Получение текста из текстового поля.
        String text = textField.getText();

        //Change - переменная, указывающая на прыжок. Если CTRL зажат, то прыжок идёт до первого пробела, а если нет - то на 1 порядок.
        int to_before = to, change = ctrl ? selectAllInRight(text, to) : 1;

        //Изменение наводящего курсора.
        to += change;
        if (!shift) {//Если SHIFT не нажат...
            if (!together) {//И курсор не единичный (имеет выделенную зону)...
                to = from = Math.max(from, to_before);//То курсор прыгает максимально вправо.
            } else {//Иначе (если курсор единичный)...
                from = to;//То 2 часть курсора прыгает за ведущей, что сохраняет единичность.
            }
        }

        textField.setCursor(from, to);//Обновление курсора.
    }

    /**
     * Функция реализует логику движения курсора текстового поля влево.
     * @param textField - текстовое поле.
     * @param ctrl - состояние клавиши CONTROL.
     * @param shift - состояние клавиши SHIFT.
     */
    public static void moveInLeft (ITextField textField, boolean ctrl, boolean shift) {
        //Получение текстового курсора (состоящего из 2 курсоров).
        int[] cursor = textField.getCursor();

        //Получение курсора.
        int from = cursor[0], to = cursor[1];

        //Если 2 курсора вместе.
        boolean together = from == to;

        //Получение текста из текстового поля.
        String text = textField.getText();

        //Change - переменная, указывающая на прыжок. Если CTRL зажат, то прыжок идёт до первого пробела, а если нет - то на 1 порядок.
        int to_before = to, change = ctrl ? selectAllInLeft(text, to) : 1;

        //Изменение наводящего курсора.
        to -= change;
        if (!shift) {//Если SHIFT не нажат...
            if (!together) {//И курсор не единичный (имеет выделенную зону)...
                to = from = Math.min(from, to_before);//То курсор прыгает максимально влево.
            } else {//Иначе (если курсор единичный)...
                from = to;//То 2 часть курсора прыгает за ведущей, что сохраняет единичность.
            }
        }

        textField.setCursor(from, to);//Обновление курсора.
    }

    /**
     * Функция реализует логику удаления текста.
     * @param textField - текстовое поле.
     * @param ctrl - состояние клавиши CONTROL.
     * @param shift - состояние клавиши SHIFT.
     */
    public static void backspace (ITextField textField, boolean ctrl, boolean shift) {
        //Получение текстового курсора (состоящего из 2 курсоров).
        int[] cursor = textField.getCursor();

        //Получение курсора.
        int from = cursor[0], to = cursor[1];

        //Если 2 курсора вместе.
        boolean together = from == to;

        //Получение текста из текстового поля.
        String text = textField.getText();

        if (!together) {//Если курсор не единичный (имеет выделенную зону), то...
            textField.setText(text.substring(0, Math.min(from, to)) + text.substring(Math.max(from, to)));//Удаление текста между 2 курсорами (в диапазоне).
            to = from = Math.min(to, from);//Обновление курсора (максимально влево).
        } else if (from != 0) {//Иначе если курсор не пытается удалить пустоту, что приведёт к ошибке, то...
            //Change - переменная, указывающая на прыжок. Если CTRL зажат, то прыжок идёт до первого пробела, а если нет - то на 1 порядок.
            int change = ctrl ? selectAllInLeft(text, from) : 1;

            textField.setText(text.substring(0, Math.max(0, from - change)) + text.substring(from));//Удаление change букв из текста на курсоре.
            to = from = Math.max(0, from - change);//Обновление курсора (максимально влево).
        }

        textField.setCursor(from, to);//Обновление курсора.
    }

    /**
     * Функция выполняет логику внедрения нового символа внутрь текста.
     * @param textField - текстовое поле.
     * @param event - ивент нажатия клавиши.
     * @param shift - состояние клавиши SHIFT.
     */
    public static void enterChar (ITextField textField, KeyEvent event, boolean ctrl, boolean shift) {
        char c = event.getKeyChar();

        if (ctrl) {//Если CTRL нажат, то...
            updateBinds(textField, event);
        }
        else if (!charIsASpecial(c)) {//Если символ подходящий для вывода, то...
            //Получение символа в виде строки.
            String newChar = shift ? String.valueOf(c).toUpperCase() : String.valueOf(c).toLowerCase();

            enterString(textField, newChar);
        }
    }

    public static void enterString(ITextField textField, String s) {
        //Получение текстового курсора (состоящего из 2 курсоров).
        int[] cursor = textField.getCursor();

        //Получение курсора.
        int from = cursor[0], to = cursor[1];

        //Получение текста из текстового поля.
        String text = textField.getText();

        //Вставление нового символа между 2 курсорами.
        text = text.substring(0, Math.min(from, to)) + s + text.substring(Math.max(from, to));

        //Обновление курсора (сдвигает обе точки курсора перед новым символом).
        from = to = Math.min(from, to) + s.length();

        textField.setText(text);//Обновление текста.
        textField.setCursor(from, to);//Обновление курсора.
    }

    public static void updateBinds(ITextField textField, KeyEvent event) {
        //Получение текстового курсора (состоящего из 2 курсоров).
        int[] cursor = textField.getCursor();

        //Получение курсора.
        int from = cursor[0], to = cursor[1];

        //Получение текста из текстового поля.
        String text = textField.getText();

        //Получение нажатого символа, с применением toLowerCase
        String s = String.valueOf((char)event.getKeyCode()).toLowerCase();

        //Разбитие текста на 3 части (от 0 до from, от from до to, и от to до конца).
        String textInCursor = text.substring(Math.min(from, to), Math.max(from, to)), partFrom = text.substring(0, Math.min(from, to)), partTo = text.substring(Math.max(from, to));

        switch (s) {//Проверка нажатого символа.
            case "c" -> {//Копирование выделенного текста.
                if (!textInCursor.isEmpty()) {
                    ClipBoardUtils.copyToClipboard(textInCursor);
                }
            }
            case "v" -> {//Вставление ранее скопированного текста в поле курсора.
                String newPart = ClipBoardUtils.getFromClipboard();
                text = partFrom + newPart + partTo;
                from = to = newPart.length() + Math.min(from, to);
            }
            case "x" -> {//Копирование + вырезание.
                text = partFrom + partTo;
                ClipBoardUtils.copyToClipboard(textInCursor);
                from = to = Math.min(from, to);
            }
        }

        textField.setText(text);//Обновление текста.
        textField.setCursor(from, to);//Обновление курсора.
    }

    /**
     * Функция находит дистанцию от текущего индекса до ближайшего пробела вправо.
     * @param s - строка, в который производится поиск.
     * @param from - начальный индекс.
     * @return дистанция (сколько символом стоит между ближайшим пробелом и текущим индексом).
     */
    public static int selectAllInRight(String s, int from) {
        boolean firstSpaces = true;

        for (int i = from; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ' ') {
                firstSpaces = false;
            }

            if (firstSpaces) {
                continue;
            }

            if (c == ' ') {
                return i - from;
            }
        }

        return s.length() - from;
    }

    /**
     * Функция находит дистанцию от текущего индекса до ближайшего пробела влево.
     * @param s - строка, в который производится поиск.
     * @param from - начальный индекс.
     * @return дистанция (сколько символом стоит между ближайшим пробелом и текущим индексом).
     */
    public static int selectAllInLeft(String s, int from) {
        boolean firstSpaces = true;

        for (int i = from; i >= 1; i--) {
            if (i >= s.length()) {
                continue;
            }

            char c = s.charAt(i - 1);

            if (c != ' ') {
                firstSpaces = false;
            }

            if (firstSpaces) {
                continue;
            }

            if (c == ' ') {
                return from - i;
            }
        }

        return from;
    }

    /**
     * Функция проверяет существование символа, возможно-ли его вывести.
     * @param c - символ.
     * @return - состояние true или false.
     */
    public static boolean charIsASpecial (char c) {
        return c == '\uFFFF' || c == '\n' || c == '\r' || c == KeyEvent.VK_ESCAPE || c == KeyEvent.VK_BACK_SPACE;
    }
}