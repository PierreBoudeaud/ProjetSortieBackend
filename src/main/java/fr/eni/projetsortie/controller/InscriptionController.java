package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Inscription;
import fr.eni.projetsortie.model.InscriptionId;
import fr.eni.projetsortie.service.InscriptionServiceImp;
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
@RequestMapping(path = "/inscriptions")
@Api(value = "Inscriptions")
public class InscriptionController {
    @Autowired
    private InscriptionServiceImp inscriptionService;

    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @RequestMapping()
    public List<Inscription> getInscriptions(){
        return this.inscriptionService.list();
    }

    @RequestMapping("/{idSortie}/{idParticipant}/")
    @ResponseBody
    public ResponseEntity<Inscription> getSortie(@PathVariable("idSortie") int idSortie, @PathVariable("idParticipant") int idParticipant){
        InscriptionId inscriptionId = new InscriptionId(idSortie,idParticipant);
        Inscription inscription;
        ResponseEntity<Inscription> response;

        inscription = this.inscriptionService.get(inscriptionId);

        if(inscription != null){
            response = ResponseEntity.ok().body(inscription);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /*@RequestMapping()
    public ResponseEntity<List<Inscription>> getInscriptionOfSortie(@RequestParam("sortieId") int sortieId){
        List<Inscription> inscriptions = this.inscriptionService.list();
        return ResponseEntity.ok(inscriptions);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Inscription> createInscription (@RequestBody Inscription newInscription,
                                                     UriComponentsBuilder uri){

        newInscription.setId( (InscriptionId) this.inscriptionService.save(newInscription));
        URI location =
                uri.path("/sorties/")
                        .path(String.valueOf(newInscription.getId()))
                        .build()
                        .toUri();
        this.logger.debug("Create sortie " + location);
        return ResponseEntity.created(location).body(newInscription);
    }
}
