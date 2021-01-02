package pl.kasia.tutorial.springspotify.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kasia.tutorial.springspotify.security.services.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = parseJwt(httpServletRequest);
            if(token != null && tokenUtils.validateToken(token)){
                String username = tokenUtils.getUsernameFromToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (UsernameNotFoundException e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String parseJwt(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");

        if(StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7,header.length());
        }
        return null;
    }
}
