package com.example.zoutohanafansitedemo.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 1. トークンがない、またはBearerで始まらない場合は、何もせず次のフィルターへ流す
        // (ここでreturnしてはいけません。filterChain.doFilterを呼ぶ必要があります)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. トークンの解析を試みる
        try {
            jwt = authHeader.substring(7);
            username = jwtService.getUsernameFromToken(jwt);

            // 3. ユーザー名が取得でき、かつ現在のコンテキストに認証情報がない場合
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // 4. トークンが有効であれば、セキュリティコンテキストに認証情報をセットする
                if (jwtService.validateToken(jwt, username)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // ここでセットすることで「ログイン済み」状態になる
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // ★重要ポイント★
            // トークンの期限切れや不正な形式などで例外が出ても、ここではエラーレスポンス(403)を返さない。
            // ログだけ出力し、"未認証状態" のまま処理を続行させる。
            // これにより、SecurityConfigで permitAll() されているエンドポイントはアクセス可能になる。
            logger.warn("JWT Authentication failed: " + e.getMessage());
        }

        // 5. 最終的に必ず次のフィルター(Controllerなど)へリクエストを渡す
        filterChain.doFilter(request, response);
    }
}