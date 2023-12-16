package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class newone2 {
        public static void main(String[] args) throws IOException {
            String filePath = "C:\\Users\\rajkumar.selvaraj\\Downloads\\OTO capital 1";
            FileReader fileReader = new FileReader(filePath);
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject json1 = new JSONObject(tokener);
            String json = json1.toString();
//            String json = "{\n" +
//                    "  \"person\": {\n" +
//                    "    \"name\": \"Alice\",\n" +
//                    "    \"age\": 30,\n" +
//                    "    \"address\": {\n" +
//                    "      \"city\": \"London\",\n" +
//                    "      \"postalCode\": \"SW1A 1AA\"\n" +
//                    "    }\n" +
//                    "  },\n" +
//                    "  \"pets\": [\n" +
//                    "    {\n" +
//                    "      \"name\": \"Fluffy\",\n" +
//                    "      \"type\": \"Cat\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"name\": \"Buddy\",\n" +
//                    "      \"type\": \"Dog\"\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}";

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            if (rootNode.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
                StringBuilder result = new StringBuilder();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    processJsonNode(entry.getKey(), entry.getValue(), result);
                }
                System.out.println(result.toString());
                FileWriter fileWriter = new FileWriter("E:\\files\\result.json");
                fileWriter.write(result.toString());
                fileWriter.close();
            }
        }
        private static void processJsonNode(String currentPath, JsonNode node, StringBuilder result) {
            if (node.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    processJsonNode(currentPath + "-" + entry.getKey(), entry.getValue(), result);
                }
            }
            else if (node.isArray()) {
                int index = 0;
                for (JsonNode element : node) {
                    processJsonNode(currentPath + "[" + index + "]", element, result);
                    index++;
                }
            } else {
                result.append(currentPath).append(" : ").append(node.asText()).append(",\n");
            }
        }
    }