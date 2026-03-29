package program.equations.utils;

import program.equations.chain.chains.Div;
import program.equations.chain.chains.Mul;
import program.equations.chain.chains.Sub;
import program.equations.chain.chains.Sum;
import program.equations.chain.chains.bot1.conditions.IfElseFunc;
import program.equations.chain.chains.bot1.conditions.NullFunc;
import program.equations.chain.chains.bot1.logical.AndFunc;
import program.equations.chain.chains.bot1.logical.BiggerFunc;
import program.equations.chain.chains.bot1.logical.LowerFunc;
import program.equations.chain.chains.bot1.logical.OrFunc;
import program.equations.chain.chains.functions.Absolute;
import program.equations.chain.chains.functions.Logarithm;
import program.equations.chain.chains.functions.Pow;
import program.equations.chain.chains.functions.Sqrt;
import program.equations.chain.chains.functions.comparison.Maximal;
import program.equations.chain.chains.functions.comparison.Minimal;
import program.equations.chain.chains.functions.constant.E;
import program.equations.chain.chains.functions.constant.Pi;
import program.equations.chain.chains.functions.trigonometry.Cosine;
import program.equations.chain.chains.functions.trigonometry.Sine;
import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;
import program.equations.value.values.Specific;
import program.equations.value.values.XValue;
import program.plugin.Configure;
import program.plugin.utils.LoadPluginUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringBuilderUtils {
    protected static HashMap<String,IFunction> functions = new HashMap<>();

    /**
     * Функция выполняет парсинг строки, превращая её в последовательность действий.
     * Она делает это в 4 этапа.
     * Промежуточные данные функция сохраняет в хранилище, и в уравнение вставляет ключ-ссылку на ячейку в хранилище.
     * 5 + 4 - 3 -> (надо обработать 5 + 4), 5 + 4 добавляется в хранилище, и вместо этого вставляется ключ.
     * <0> - 3 -> (надо обработать <0> - 3), алгоритм читает ключ, и ссылается на выражение 5 + 4. Теперь <0> - 3 вкладывается в ячейку хранилища, и строка заменяется на <1>.
     * В конце, когда все операции были сделаны, код прочитает <1>, и вернёт набор действий.
     * По итогу в этой ячейке будет лежать последовательность действий, и обычный вызов getValue высчитает уравнение.
     * @param s - строка с уравнением. Она должна быть составлена правильно. Синтаксис как в Java. (Например парабола пишется как pow(x, 2)).
     * @return объект класса IValue, может ссылаться на другие IValue. Этот класс может быть определённым (1,2,3,4), не определённым (x) и функциональным (1 + 2, 3 * sin(30), и тд).
     * @throws IOException - ошибки синтаксиса в строчке / внутренние ошибки алгоритма (Если функция написана неправильно, методом отладки через ошибки можно будет устранить ошибку).
     */
    public static IValue getChain(String s) throws IOException {
        if (s.isEmpty()) {
            throw new IOException("Equation is empty.");
        }

        return firstStage(s);
    }

    /**
     * 1 шаг алгоритма - это подготовка строки.
     * Функция удаляет лишние пробелы, и делает все буквы маленькими.
     */
    public static IValue firstStage (String s) throws IOException {
        if (s.isEmpty()) {
            return null;
        }

        return secondStage(insertSpaces(s));
    }

    /**
     * Второй шаг - это открыть скобки.
     * Функция учитывает функции.
     * Этап рекурсивно вызывает сам-себя для выражения в скобках.
     */
    public static IValue secondStage (String s) throws IOException {
        int parentheses = 0, from = 0;

        int free_storage_index = 0;

        HashMap<Integer, IValue> storage = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                parentheses++;

                if (parentheses == 1) {
                    from = i;
                }
            } else if (c == ')') {
                parentheses--;
                if (parentheses != 0) {

                    continue;
                }

                String function_syntax = getFunctionNearToParentheses(s, from);

                IFunction function = functions.get(function_syntax);

                if (!function_syntax.isEmpty() && function == null) {
                    throw new IOException("Function '" + function_syntax + "' is not registered. Register function with .addFunction().");
                }

                String s_inside_of_parentheses = s.substring(from + 1, i);

                IValue value;

                if (function != null) {
                    value = function.getChain(s_inside_of_parentheses);
                } else {
                    value = secondStage(s_inside_of_parentheses);
                }

                storage.put(free_storage_index, value);

                s = insertKey(from - function_syntax.length(), i + 1, free_storage_index, s);

                i = 0;

                free_storage_index++;
            }
        }

        if (s.startsWith(Sub.syntax+"")) {
            s = "0" + s;
        }

        return thirdStage(s, storage, free_storage_index);
    }

    /**
     * Третий этап выполняет умножение и деление.
     */
    public static IValue thirdStage (String s, HashMap<Integer, IValue> storage, int free_storage_index) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (charIsABasicOperatorOfSecondDischarge(c)) {
                String val1_s = getLeftValue(s, i), val2_s = getRightValue(s, i);

                IValue val1 = getValueFromString(val1_s, storage), val2 = getValueFromString(val2_s, storage);

                if (val1 == null || val2 == null) {
                    return null;
                }

                IValue value = null;

                switch (c) {
                    case Mul.syntax -> value = new Mul(val1, val2);
                    case Div.syntax -> value = new Div(val1, val2);
                }

                if (value == null) {
                    throw new IOException("Unknown operation of second discharge: " + c + ".");
                }

                storage.put(free_storage_index, value);

                s = insertKey(i - val1_s.length(), i + val2_s.length() + 1, free_storage_index, s);

                i = 0;

                free_storage_index++;
            }
        }

        return fourthStage(s, storage, free_storage_index);
    }

    /**
     * Четвёртый этап выполняет сложение и вычитание.
     */
    public static IValue fourthStage (String s, HashMap<Integer, IValue> storage, int free_storage_index) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (charIsABasicOperatorOfFirstDischarge(c)) {
                String val1_s = getLeftValue(s, i), val2_s = getRightValue(s, i);

                IValue val1 = getValueFromString(val1_s, storage), val2 = getValueFromString(val2_s, storage);

                if (val1 == null || val2 == null) {
                    return null;
                }

                IValue value = null;

                switch (c) {
                    case Sum.syntax -> value = new Sum(val1, val2);
                    case Sub.syntax -> value = new Sub(val1, val2);
                }

                if (value == null) {
                    throw new IOException("Unknown operation of first discharge: " + c + ".");
                }

                storage.put(free_storage_index, value);

                s = insertKey(i - val1_s.length(), i + val2_s.length() + 1, free_storage_index, s);

                i = 0;

                free_storage_index++;
            }
        }

        return getValueFromString(s, storage);
    }

    public static IValue getValueFromString (String s, HashMap<Integer, IValue> storage) throws IOException{
        if (s.startsWith("<") && s.endsWith(">")) {
            Float val = getFloatFromString(s.substring(1, s.length() - 1));

            if (val == null) {
                throw new IOException("Key "+s+" has syntax error (Correct <id>).");
            }

            int id = val.intValue();

            return storage.get(id);
        }
        else if (s.equalsIgnoreCase("x")) {
            return new XValue();
        }
        else {
            Float val = getFloatFromString(s);

            if (val == null) {
                throw new IOException("Specific value " + s + " has syntax error.");
            }

            return new Specific(val);
        }
    }

    public static String getLeftValue(String s, int index) {
        for (int i = index - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (charIsABasicOperator(c)) {
                return s.substring(i + 1, index);
            }
        }

        return s.substring(0, index);
    }

    public static String getRightValue(String s, int index) {
        for (int i = index + 1; i < s.length(); i++) {
            char c = s.charAt(i);

            if (charIsABasicOperator(c)) {
                return s.substring(index + 1, i);
            }
        }

        return s.substring(index + 1);
    }

    public static Float getFloatFromString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        boolean value_is_separated = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (charIsAFloatSeparator(c) && i != 0 && i != s.length() - 1) {
                if (value_is_separated) {
                    return null;
                }

                value_is_separated = true;
            } else if (!charIsANumber(c)) {
                return null;
            }
        }

        return Float.parseFloat(s);
    }

    public static String insertKey (int from, int to, int id, String s) {
        return s.substring(0, from) + "<" + id + ">" + s.substring(to);
    }

    public static String getFunctionNearToParentheses(String s, int index) {
        int from = 0;

        for (int i = index; i >= 0; i--) {
            char c = s.charAt(i);

            if (charIsABasicOperator(c)) {
                from = i + 1;
                break;
            }
        }

        return s.substring(from, index);
    }

    public static List<String> separate(String s, char separation_char) {
        int parentheses = 0;

        List<String> separated_parts = new ArrayList<>();

        StringBuilder part = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == separation_char && parentheses == 0) {
                separated_parts.add(part.toString());
                part.setLength(0);
            } else {
                part.append(c);
            }

            if (c == '(') {
                parentheses++;
            }
            else if (c == ')') {
                parentheses--;
            }
        }

        if (!s.isEmpty()) {
            separated_parts.add(part.toString());
        }

        return separated_parts;
    }

    public static String insertSpaces(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                result.append(s.charAt(i));
            }
        }

        return result.toString().toLowerCase();
    }

    public static boolean charIsABasicOperatorOfSecondDischarge(char c) {
        return c == Mul.syntax || c == Div.syntax;
    }

    public static boolean charIsABasicOperatorOfFirstDischarge(char c) {
        return c == Sum.syntax || c == Sub.syntax;
    }

    public static boolean charIsABasicOperator(char c) {
        return c == Sum.syntax || c == Sub.syntax || c == Mul.syntax || c == Div.syntax;
    }

    public static boolean charIsANumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean charIsAFloatSeparator(char c) {
        return c == '.';
    }

    public static void setFunctions(HashMap<String, IFunction> functions) {
        StringBuilderUtils.functions.clear();
        StringBuilderUtils.functions.putAll(functions);
    }

    public static void addFunction(String syntax, IFunction function) {
        functions.put(syntax.toLowerCase(), function);
    }

    public static IFunction getFunction (String syntax) {
        return functions.get(syntax);
    }

    public static void init () {
        //basic
        StringBuilderUtils.addFunction(Sqrt.syntax, new Sqrt(null));
        StringBuilderUtils.addFunction(Pow.syntax, new Pow(null, null));
        StringBuilderUtils.addFunction(Absolute.syntax, new Absolute(null));
        StringBuilderUtils.addFunction(Logarithm.syntax, new Logarithm(null, null));

        //const
        StringBuilderUtils.addFunction(E.syntax, new E());
        StringBuilderUtils.addFunction(Pi.syntax, new Pi());

        //trigonometry
        StringBuilderUtils.addFunction(Sine.syntax, new Sine(null));
        StringBuilderUtils.addFunction(Cosine.syntax, new Cosine(null));

        //comparison
        StringBuilderUtils.addFunction(Maximal.syntax, new Maximal(null, null));
        StringBuilderUtils.addFunction(Minimal.syntax, new Minimal(null, null));

        StringBuilderUtils.addFunction(NullFunc.syntax, new NullFunc());
        StringBuilderUtils.addFunction(OrFunc.syntax, new OrFunc(null, null));
        StringBuilderUtils.addFunction(AndFunc.syntax, new AndFunc(null, null));
        StringBuilderUtils.addFunction(IfElseFunc.syntax, new IfElseFunc(null, null, null));
        StringBuilderUtils.addFunction(BiggerFunc.syntax, new BiggerFunc(null, null));
        StringBuilderUtils.addFunction(LowerFunc.syntax, new LowerFunc(null, null));

        try {
            Configure configure = LoadPluginUtils.bootstrapPlugin(new File("plugins/"), 1);

            for (Map.Entry<String, IFunction> entry : configure.getFunctions().entrySet()) {
                String key = entry.getKey();
                IFunction value = entry.getValue();
                StringBuilderUtils.addFunction(key, value);
            }
        } catch (IOException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
