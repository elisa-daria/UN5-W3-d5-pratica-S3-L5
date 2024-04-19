package elisadaria.UN5W3d5praticaS3L5.security;

import elisadaria.UN5W3d5praticaS3L5.entities.User;
import elisadaria.UN5W3d5praticaS3L5.exceptions.UnAuthorizedEx;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {
    @Value("${jwt.secret}")
    private String secret;
    public String creatingT(User user){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*14))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public void verifyT(String token){
        try{
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        }catch (Exception ex){
            throw new UnAuthorizedEx(ex.getMessage());
        }
    }
    public String extractT(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token).getPayload().getSubject();
    }
}
