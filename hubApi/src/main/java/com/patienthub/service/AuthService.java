package com.patienthub.service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.patienthub.data.UserDao;
import com.patienthub.model.Credentials;
import com.patienthub.model.User;
import com.patienthub.webexceptions.PasswordMismatch;
import com.patienthub.webexceptions.UserDoesNotExist;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthService {

    private static Dotenv dotenv = Dotenv.load();
    private static final String SECRET_KEY = dotenv.get("JERSEY_SECERET");
    private int accessTokenExpireDays;

    private static int ACCESS_TOKEN_EXPIRE_DAY = 90;

    public AuthService() {
        this.accessTokenExpireDays = ACCESS_TOKEN_EXPIRE_DAY;

    }

    public int getAccessTokenExpireDays() {
        return accessTokenExpireDays;
    }

    public void setAccessTokenExpireDays(int numberOfDays) {
        accessTokenExpireDays = numberOfDays;
    }

    public String authenticate(Credentials credentials) {
        String email = credentials.getEmail();
        String password = credentials.getPassword();

        UserDao userDao = new UserDao();
        User user = userDao.getUserbyEmail(email);

        if (user == null) {
            throw new UserDoesNotExist();
        }

        if (user.passwordValid(password) == false) {
            throw new PasswordMismatch();
        }

        return issueToken(user);
    }

    private String issueToken(User user) {

        long nowMillis = System.currentTimeMillis();
        // 3 months
        Date now = new Date(nowMillis);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, accessTokenExpireDays);

        Date expiration = calendar.getTime();

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        String fullName = user.getFirstName() + " " + user.getLastName();

        Claims claims = Jwts.claims();
        claims.put("role", user.getClass().getSimpleName());

        String jws = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(fullName)
                .setId(user.getPps())
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
        return jws;
    }

}
