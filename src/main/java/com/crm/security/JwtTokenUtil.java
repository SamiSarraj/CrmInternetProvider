package com.crm.security;

import com.crm.domain.User;
import com.crm.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import static com.crm.security.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.crm.security.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable {

    private final UserService userService;

    @Autowired
    public JwtTokenUtil(UserService userService) {
        this.userService = userService;
    }

    public Date getIssuedAtDateFromToken(AuthorizationToken token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public String getUsernameFromToken(AuthorizationToken token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(AuthorizationToken token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(AuthorizationToken token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(AuthorizationToken token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token.getToken())
                .getBody();
    }

    private Boolean isTokenExpired(AuthorizationToken token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", userService.getAuthority(user.getUsername()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://netcrm.pl")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(AuthorizationToken token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }
    /*  private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }*/

    public String refreshToken(AuthorizationToken token) {

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

}
