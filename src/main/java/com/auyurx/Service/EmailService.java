package com.auyurx.Service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendEmaiWithHtml();

    void sendEmailWithAttachment();
}