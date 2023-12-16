package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class newone {
    public static void main(String[] args) throws IOException {
        String json ="{\n" +
                "   \"Name\":\"Craig\",\n" +
                "   \"Age\":10,\n" +
                "   \"BookInterests\":[\n" +
                "      {\n" +
                "         \"Book\":\"The Kite Runner\",\n" +
                "         \"Author\":\"Khaled Hosseini\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"Book\":\"Harry Potter\",\n" +
                "         \"Author\":\"J. K. Rowling\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"FoodInterests\":{\n" +
                "      \"Breakfast\":[\n" +
                "         {\n" +
                "            \"Bread\":\"Whole wheat\",\n" +
                "            \"Beverage\":\"Fruit juice\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Sandwich\":\"Vegetable Sandwich\",\n" +
                "            \"Beverage\":\"Coffee\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";
//        List<String> keys= new ArrayList<>();
        ObjectMapper objectMapper=new ObjectMapper();
        newone newone = new newone();
        newone.getKeysInJsonUsingJsonNodeFieldNames(json,objectMapper);
//        try {
//            keys = new newone().getKeysInJsonUsingJsonNodeFieldNames(json, objectMapper);
//            System.out.println(keys);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        List<String> values = new newone().getValuesInJsonUsingJsonNodeFieldNames(json,keys,objectMapper);
//        System.out.println(values);
    }
    public  void getKeysInJsonUsingJsonNodeFieldNames(String json, ObjectMapper mapper) throws IOException {
        List<String> keys=new ArrayList<>();
        JsonNode jsonNode = mapper.readTree(json);
        Iterator<String> iterator = jsonNode.fieldNames();
        iterator.forEachRemaining(e -> keys.add(e));
        keys.forEach(
                e ->
                {
//                    try {
                    try {
                        System.out.println(findkeyasobjectornot(e,json,mapper)+" : "+jsonNode.get(e).asText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
//                    } catch (JsonProcessingException ex) {
//                        throw new RuntimeException(ex);
//                    }
                }
        );
    }
    public String findkeyasobjectornot(String e,String json, ObjectMapper mapper) throws IOException {
        JsonNode isarray = mapper.readTree(json);
        JsonNode isarray1 = isarray.path(e);
        if (isarray1.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = isarray1.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                System.out.println(entry.getKey());
            }
            return "object";
        }
//        else if (isarray1.isArray()) {
//            String hobby1=" ";
//            for (JsonNode hobby : isarray1) {
//                hobby1 = hobby1+hobby.asText();
//            }
//            return hobby1;
//        }
        else {
            return e;
        }
    }
//    public List<String> getValuesInJsonUsingJsonNodeFieldNames(String json,List<String> keys, ObjectMapper mapper) throws JsonProcessingException, JsonProcessingException {
//
//        Iterator iterator = keys.iterator();
//        List<String> values = new ArrayList<>();
//        while (iterator.hasNext()) {
//            String key = (String) iterator.next();
//            JsonNode jsonNode = mapper.readTree(json);
//            values.add(jsonNode.get(key).asText());
//        }
//        return values;
//    }
}
