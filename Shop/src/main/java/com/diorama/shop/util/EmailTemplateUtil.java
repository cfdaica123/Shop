package com.diorama.shop.util;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EmailTemplateUtil {
    public static String loadTemplate(String fileName, Map<String, String> placeholders) {
        try {
            InputStream inputStream = EmailTemplateUtil.class
                .getClassLoader()
                .getResourceAsStream("templates/" + fileName);

            if (inputStream == null) {
                throw new RuntimeException("Template not found: " + fileName);
            }

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            return content;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load email template: " + fileName, e);
        }
    }
}
