package com.jrrtriangle.ams.filter;

import com.jrrtriangle.ams.entity.Privilege;
import com.jrrtriangle.ams.service.RoleService;
import com.jrrtriangle.ams.service.UserService;
import com.jrrtriangle.ams.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Entering from first");
        String authorization = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authorization !=null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            username = jwtUtility.getUsernameFromToken(token);

        }
        if(username !=null && SecurityContextHolder.getContext().getAuthentication() ==null){
            UserDetails userDetails = userService.loadUserByUsername(username);
            String requestUrl = request.getRequestURI();
            System.out.println("Printing the requested  uri: "+requestUrl);
            boolean access = false;
            for(GrantedAuthority grantedAuthority:userDetails.getAuthorities()){
                Set<Privilege> privilege = roleService.findRoleByRole(grantedAuthority
                        .getAuthority()).getPrivileges();
                for(Privilege p:privilege){
                    System.out.println("Privileged url :"+p.getEndpoint().toString());
                    if(p.getEndpoint().toString().equals(requestUrl)){
                        access=true;
                        break;
                    }
                }
            }
            if(access) {
                System.out.println("Accessible: "+access);
                if (jwtUtility.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

        }
        filterChain.doFilter(request,response);

    }
}
