package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Participant;
import fr.eni.projetsortie.model.Token;
import fr.eni.projetsortie.service.ParticipantServiceImp;
import fr.eni.projetsortie.service.TokenServiceImp;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("session")
public class SessionController {
    private final static Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @Autowired
    private TokenServiceImp tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ParticipantServiceImp participantService;

    @RequestMapping()
    public ResponseEntity<Token> getToken(HttpServletRequest request) {
        /*AuthenticationImp authentication = (AuthenticationImp) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        Token token = (Token) authentication.getCredentials();*/
        Token token = (Token) request.getAttribute("token");
        return ResponseEntity.ok(token);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Token> createSession(@RequestParam(value = "email", defaultValue = "", required = false) String email,
                                               @RequestParam(value = "pseudo", defaultValue = "", required = false) String pseudo,
                                               @RequestParam("password") String password,
                                               @RequestParam(value = "remember", defaultValue="0") boolean remember,
                                               UriComponentsBuilder uri) {
        Participant participant;
        ResponseEntity<Token> response;
        participant = this.participantService.findByPseudoOrEmail(pseudo, email);
        System.out.println(password);
        System.out.println(participant.getPassword());
        System.out.println(this.passwordEncoder.matches(password, participant.getPassword()));
        if(participant != null && this.passwordEncoder.matches(password, participant.getPassword())){
            this.logger.debug(participant.toString());
            Token token = new Token(participant, remember);
            this.tokenService.save(token);
            URI location =
                    uri.path("/session")
                            .build()
                            .toUri();
            this.logger.debug("Create session " + location);
            response = ResponseEntity.created(location).body(token);
        }
        else {
            this.logger.debug("Invalid credentials");
            System.out.println("Invalid credentials");
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Token> removeSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Token token = (Token) auth.getCredentials();
        this.tokenService.delete(token);
        return ResponseEntity.ok(token);
    }
}
