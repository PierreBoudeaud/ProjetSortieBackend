package fr.eni.projetsortie.security;

import fr.eni.projetsortie.model.Participant;
import fr.eni.projetsortie.model.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationImp implements Authentication {
    private Token token;

    public AuthenticationImp(Token token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.token.getParticipant();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.token.getParticipant().getPseudo();
    }

    @Override
    public String toString() {
        return "AuthenticationImp{" +
                "token=" + token +
                '}';
    }
}
