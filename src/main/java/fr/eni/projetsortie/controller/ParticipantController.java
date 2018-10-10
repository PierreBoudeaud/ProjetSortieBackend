package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Participant;
import fr.eni.projetsortie.service.ParticipantServiceImp;
import fr.eni.projetsortie.service.SiteServiceImp;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/participants")
@Api(value = "Participants")
public class ParticipantController {
    @Autowired
    private ParticipantServiceImp participantService;

    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @RequestMapping()
    public List<Participant> getParticipants(){
        return this.participantService.list();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Participant> getParticipant(@PathVariable("id") int id) {
        Participant participant = null;
        ResponseEntity<Participant> response;

        participant = this.participantService.get(id);

        if(participant != null) {
            response = ResponseEntity.ok().body(participant);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    /*@RequestMapping(params = "pseudo")
    public Participant getParticipantByPseudoOrEmail(@RequestParam("pseudo") String pseudo) {
        return this.participantService.(pseudo);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant newParticipant,
                                                         UriComponentsBuilder uri) {
        //Site site = this.siteDAOImp.get(idSite);
        //Participant newParticipant = new Participant(pseudo, email, nom, prenom, site, telephone, password, admin, actif);
        this.participantService.cryptPassword(newParticipant);

        newParticipant.setId(this.participantService.save(newParticipant));
        URI location =
                uri.path("/participants/")
                        .path(String.valueOf(newParticipant.getId()))
                        .build()
                        .toUri();
        this.logger.debug("Create participant " + location);
        return ResponseEntity.created(location).body(newParticipant);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Participant> updateParticipant(@PathVariable("id") int id,
                                                         @RequestBody Participant participant){
        this.participantService.update(participant);
        this.logger.debug("Update participant " + id);
        return ResponseEntity.ok(participant);
    }
}
