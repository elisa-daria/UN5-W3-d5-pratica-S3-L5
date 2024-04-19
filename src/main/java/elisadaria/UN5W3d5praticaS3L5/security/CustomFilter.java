package elisadaria.UN5W3d5praticaS3L5.security;

import elisadaria.UN5W3d5praticaS3L5.entities.User;
import elisadaria.UN5W3d5praticaS3L5.exceptions.UnAuthorizedEx;
import elisadaria.UN5W3d5praticaS3L5.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private JWT jwtTool;
    @Autowired
    UsersService usersService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String aHeader=request.getHeader("Authorization");
        if(aHeader==null|| !aHeader.startsWith("Bearer "))
        throw new UnAuthorizedEx("manca il token negli headers");
        String aToken=aHeader.substring(7);
        jwtTool.verifyT(aToken);
        String id= jwtTool.extractT(aToken);
        User current=this.usersService.findById(Long.valueOf(id));
        Authentication auth=new UsernamePasswordAuthenticationToken(current,null,current.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/auth/**",request.getServletPath());
    }
}
