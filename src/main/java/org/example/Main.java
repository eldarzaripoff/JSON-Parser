package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        //System.out.println(json);
        List<Employee> list = jsonToList(json);
        list.forEach(System.out::println);
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            //System.out.println(jsonArray.toJSONString());
            for (Object o : jsonArray) {
                String result = o.toString();
                //System.out.println(result);
                Employee employee = gson.fromJson(result, Employee.class);
                list.add(employee);
            }
        } catch (ParseException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return list;
    }

    private static String readString(String s) {
        JSONParser parser = new JSONParser();
        String result;
        try {
            Object object = parser.parse(new FileReader(s));
            JSONArray jsonArray = (JSONArray) object;
            result = java.util.Arrays.toString(jsonArray.toArray());
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
