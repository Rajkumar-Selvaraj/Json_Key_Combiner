package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class final12 {
    public static void main(String[] args) throws IOException, JSONException {
        String filePath = "C:\\Users\\rajkumar.selvaraj\\Downloads\\13122023JSONOutput";
            FileReader fileReader = new FileReader(filePath);
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject json1 = new JSONObject(tokener);
            String json = json1.toString();
//        String json = "{\n" +
//                "  \"person\": {\n" +
//                "    \"name\": \"Alice\",\n" +
//                "    \"age\": 30,\n" +
//                "    \"address\": {\n" +
//                "      \"city\": \"London\",\n" +
//                "      \"postalCode\": \"SW1A 1AA\"\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"pets\": [\n" +
//                "    {\n" +
//                "      \"name\": \"Fluffy\",\n" +
//                "      \"type\": \"Cat\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"name\": \"Buddy\",\n" +
//                "      \"type\": \"Dog\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        if (rootNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            StringBuilder add= new StringBuilder();
           while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                newmethod123(entry.getKey(), entry.getValue(), add);
            }
            System.out.println(add.toString());
            FileWriter fileWriter = new FileWriter("E:\\files\\result.json");
            fileWriter.write(add.toString());
            fileWriter.close();
        }
    }
    private static void newmethod123(String key, JsonNode value, StringBuilder add) {
        if (value.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = value.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                newmethod123(key + " | " + entry.getKey(), entry.getValue(), add);
            }
        } else if (value.isArray()) {
            int index = 0;
            for (JsonNode element : value) {
                newmethod123(key + "[" + index + "]", element, add);
                index++;
            }
        } else {
            add.append(key).append(" : ").append(value.asText()).append(",\n");
        }
    }
}