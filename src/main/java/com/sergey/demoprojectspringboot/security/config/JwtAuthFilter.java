package com.sergey.demoprojectspringboot.security.config;

import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.security.entity.MyEmployeeToEmployeeDetails;
import com.sergey.demoprojectspringboot.security.service.CustomUserDetailService;
import com.sergey.demoprojectspringboot.security.service.InvalidJwtException;
import com.sergey.demoprojectspringboot.security.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            final String uri = request.getRequestURI();

            final boolean isReactivateRequest = "PUT".equalsIgnoreCase(request.getMethod())
                    && "/api/user/reactivateMe".equals(uri);

            String jwt = getTokenFromRequest(request);


            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
                // создаем объект UserDetail, который понимает Spring Security наполнив его данными нашего пользователя
                String userName = jwtTokenProvider.getUsernameFromJwt(jwt);
                // получаем из jwt имя пользователя, который прислал запрос (у нас - email)
                UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
                /// *****

            if (!isReactivateRequest && userDetails instanceof MyEmployeeToEmployeeDetails details) {
                if(details.getEmployee().getStatus() == Employee.Status.INACTIVE){
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Account is deactivated");
                    return;
                }
            }


                // создаем необходимые объекты из Spring Security, чтобы наполнить SecurityContext
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }



        } catch (InvalidJwtException e) {
            System.out.println("ERROR !!! " + e.getMessage());
        }

        // обязательно надо в объект со спсиком фильтров применить добавленные изменения
        filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        /*
        если в запросе есть jwt, то тогда в теле запроса будет присутствовать
        строка, которая выглядит так: "Bearer askjhfgaskjhfgbas.asdfgareghaerhaerhaerh.arehgareharhaerhaerh"

        То есть нам надо из этой строки взять ВСЕ до конца начиная с первого символа после "Bearer "
        то есть начинаяч с 7 символа строки и до конца
         */

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
