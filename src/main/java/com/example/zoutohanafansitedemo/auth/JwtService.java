package com.example.zoutohanafansitedemo.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret; // 共通鍵(秘密鍵)

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    // JWT トークンを生成するメソッド
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .claim("roles", roles)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    // JWTトークンからユーザ名を取得
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // JWTトークンから有効期限を取得
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    // JWTトークンをパースしてクレームを取得
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // JWTトークンからロールのリストを取得
    public List<String> getRolesFromToken(String token) {
        // トークンをパースし、"roles" クレームを取得
        Claims claims = getClaimsFromToken(token);

        // ロールが List<String> として格納されていることを想定してキャスト
        // ※ JWTライブラリによっては List<?> でしか取れない場合があるので、必要に応じて型変換や null チェックを追加
        return (List<String>) claims.get("roles");
    }

    // トークンの有効期限のチェック
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // トークンの検証
    public Boolean validateToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
}
