package com.auyurx.Helpers;

public class EmailVerificationLink {
    public static String getLinkForEmailVerification(String emailToken){

        String link = "http://localhost:8080/auth/verify-email?token="+emailToken;

        return link;
    }
}
