package com.patienthub.service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.patienthub.data.UserDao;
import com.patienthub.model.Credentials;
import com.patienthub.model.WebToken;
import com.patienthub.model.User;
import com.patienthub.webexceptions.PasswordMismatch;
import com.patienthub.webexceptions.UserDoesNotExist;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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

    public WebToken authenticate(Credentials credentials) {
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

    private WebToken issueToken(User user) {

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
        claims.put("role", user.getRole());
        claims.put("iat", now);
        claims.put("sub", fullName);
        claims.put("exp", expiration);
        claims.put("jti", user.getPps());

        String jws = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();

        System.out.println(user.getPps());
        System.out.println(jws);
        WebToken token = new WebToken(jws);
        return token;
    }

    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException ex) { // (5)

            // we *cannot* use the JWT as intended by its creator
            return false;
        }

    }
}
