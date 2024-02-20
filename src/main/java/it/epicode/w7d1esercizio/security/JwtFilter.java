package it.epicode.w7d1esercizio.security;

import it.epicode.w7d1esercizio.exception.UnauthorizedException;
import it.epicode.w7d1esercizio.model.Utente;
import it.epicode.w7d1esercizio.service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private UtenteService utenteService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization==null||!authorization.startsWith("Bearer ")){
            throw new UnauthorizedException("Nessun token presente");
        }

        String token = authorization.substring(7);

        jwtTools.validateToken(token);

        String username = jwtTools.getUsernameFromToken(token);

        Utente utente = utenteService.getUtenteByUsername(username);
        //codice per autorizzare il servizio solo a utenti che cercano info sul proprio id/username
        checkPathVariable(request, utente);
        //fine gestione precedente
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
    private void checkPathVariable(HttpServletRequest request, Utente utente){
        String[] parts = request.getServletPath().split("/");
        if(parts.length==2){
            if(parts[0].equals("dipendenti")){
                int id = Integer.parseInt(parts[1]);

                if(utente.getId()!=id){
                    throw new UnauthorizedException("Non sei abilitato ad utilizzare il servizio per id differente.");
                }
            } else if (parts[0].equals("utenti")) {
                if(!utente.getUsername().equals(parts[1])){
                    throw new UnauthorizedException("Non sei abilitato ad utilizzare il servizio per un username differente.");
                }
            }
        }
    }
}