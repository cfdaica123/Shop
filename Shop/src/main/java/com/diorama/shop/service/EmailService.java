package com.diorama.shop.service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
