package br.com.fiap.alarme.config.security;

import br.com.fiap.alarme.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // 🪵 LOG do caminho da requisição
        System.out.println(">>> Filtro VerificarToken ativo para rota: " + path);

        if (
                path.equals("/swagger-ui/index.html") ||
                        path.equals("/swagger-ui.html") ||
                        path.startsWith("/v3/api-docs") ||
                        path.startsWith("/swagger-ui/") ||
                        path.startsWith("/api/teste") ||
                        path.startsWith("/swagger-resources")
        ) {
            System.out.println(">>> Rota pública, ignorando filtro.");
            filterChain.doFilter(request, response);
            return;
        }


        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = authorizationHeader.replace("Bearer", "").trim();
            String login = tokenService.validarToken(token);

            if (login != null) {
                UserDetails usuario = usuarioRepository.findByEmail(login);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                usuario.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}