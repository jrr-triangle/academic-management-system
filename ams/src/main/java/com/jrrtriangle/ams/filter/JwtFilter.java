package com.jrrtriangle.ams.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jrrtriangle.ams.entity.Privilege;
import com.jrrtriangle.ams.exception.RestResponseEntityExceptionHandler;
import com.jrrtriangle.ams.exception.TokenValidationException;
import com.jrrtriangle.ams.service.RoleService;
import com.jrrtriangle.ams.service.UserService;
import com.jrrtriangle.ams.utility.JwtUtility;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;
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
        try {
            if(authorization !=null && authorization.startsWith("Bearer ")){
                token = authorization.substring(7);
                username = jwtUtility.getUsernameFromToken(token);

            }
            if(username !=null && SecurityContextHolder.getContext().getAuthentication() ==null){
                UserDetails userDetails = userService.loadUserByUsername(username);
                String requestUrl = request.getRequestURI();
                String requestMethod = request.getMethod();
                System.out.println("Printing the requested  uri: "+requestUrl);
                System.out.println("Printing the requested  method: "+requestMethod);
                boolean access = false;
                for(GrantedAuthority grantedAuthority:userDetails.getAuthorities()){
                    Set<Privilege> privileges = roleService.findRoleByRole(grantedAuthority
                            .getAuthority()).getPrivileges();
                    for(Privilege privilege:privileges){
                        System.out.println("Privileged url :"+privilege.getEndpoint().toString());
                        System.out.println("Privileged method :"+privilege.getMethod());
                        String endPoint = privilege.getEndpoint()
                                .replaceAll("\\*+",".*")
                                .replaceAll("(?ism)\\{\\w+\\}","\\\\w+");
                        System.out.println(endPoint);
                        Pattern p = Pattern.compile("(?ism)"+endPoint);//. represents single character
                        Matcher m = p.matcher(requestUrl);
                        if(m.matches() &&
                                privilege.getMethod().equalsIgnoreCase(requestMethod)){
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

            }catch (ExpiredJwtException e){

            resolver.resolveException(request,response,null,new TokenValidationException("Token is expired"));
        }
        catch (Exception e){
            System.out.println("Error classname: "+e.getClass());
            resolver.resolveException(request,response,null,
                   e);
        }


    }
}
