package dev.sha256.mightypets.Utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class VariableSystem {


    public static String convertToInt(String equation, String variable, int value) {
        return equation.replace(variable, String.valueOf(value));
    }

    public static String convertToString(String equation, String variable, String value) {
        return equation.replace(variable, value);
    }

    public static String convertToBoolean(String equation, String variable, Boolean value) {
        return equation.replace(variable, String.valueOf(value));
    }

    public static String applyAllVariables(String text, String... variables) {
        return text.replace("{name}", variables[0]).replace("{level}", variables[1]).replace("{exp}", variables[2])
                .replace("{expbar}", variables[3]);
    }

    public static List<String> applyAllVariablesToList(List<String> list, String... variables) {
        List<String> newList = new ArrayList<>();

        for(String str : list) {
            newList.add(applyAllVariables(str, variables));
        }

        return newList;
    }

    public static String solveEquation(String equation) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");

        String solve = null;

        try {
            solve = scriptEngine.eval(equation).toString();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return solve;
    }

}
