package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonKeyValuePairsExample {
        public static void main(String[] args) throws IOException {
            String json = "{\n" +
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
            JsonNode rootNode = objectMapper.readTree(json);

            // Call the recursive method to print key-value pairs
            printKeyValuePairs(rootNode);
        }

        private static void printKeyValuePairs(JsonNode node) {
            StringBuilder key = new StringBuilder();
            if (node.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    System.out.print(entry.getKey());
                    if (fields.hasNext()) {
                        System.out.print("-");
                    }
                    // Recursive call to handle nested structures
                    printKeyValuePairs(entry.getValue());
                }
            } else if (node.isArray()) {
                for (JsonNode element : node) {
                    // Recursive call to handle elements of the array
                    printKeyValuePairs(element);
                }
            } else {
                // Handle simple values (string, number, boolean, null)
                System.out.println( key+" : "+node.asText());
            }
        }
//        public static String parentkey(String pk){
//            String
//        }
    }
