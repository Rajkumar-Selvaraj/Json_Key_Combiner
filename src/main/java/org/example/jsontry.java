package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class jsontry {
    public static void main(String[] args) throws IOException {
        String json="{\n" +
                "  \"person\": {\n" +
                "    \"name\": \"Alice\",\n" +
                "    \"age\": 30,\n" +
                "    \"address\": {\n" +
                "      \"city\": \"London\",\n" +
                "      \"postalCode\": \"SW1A 1AA\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"pets\": [\n" +
                "    {\n" +
                "      \"name\": \"Fluffy\",\n" +
                "      \"type\": \"Cat\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Buddy\",\n" +
                "      \"type\": \"Dog\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode obj=objectMapper.readTree(json);
//        System.out.println(obj);
        if (obj.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = obj.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }
        }
    }
}
