package fr.eni.projetsortie.Filter;

import fr.eni.projetsortie.model.Participant;
import fr.eni.projetsortie.model.Token;
import fr.eni.projetsortie.security.AuthenticationImp;
import fr.eni.projetsortie.service.TokenServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class AuthChecker extends OncePerRequestFilter {
    @Autowired
    private TokenServiceImp tokenService;

    private Token token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(RequestMethod.POST);
        System.out.println(request.getServletPath());
        System.out.println(request.getMethod());
        if(!request.getMethod().equals("OPTIONS") && !(request.getServletPath().equals("/session") && request.getMethod().equals(RequestMethod.POST.toString()))) {


            String xAuth = request.getHeader("X-Authorization");

            if(xAuth != null && isValid(xAuth)) {
                // Create our Authentication and let Spring know about it
                Authentication auth = new AuthenticationImp(this.token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                request.setAttribute("token", this.token);
            }
            else {
                this.error401(response);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void error401(HttpServletResponse response) throws IOException{
        CustomHeaderFilter.addHeaders(response);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is invalid");
    }

    private boolean isValid(String xAuthToken) {
        boolean valid = false;
        try{
            Token token = this.tokenService.get(xAuthToken);
            valid = token.getExpirationDate().after(new Date());
            this.token = token;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            valid = false;
        }
        return valid;
    }
}
