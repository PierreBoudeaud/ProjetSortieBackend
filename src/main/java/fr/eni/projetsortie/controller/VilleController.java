package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Ville;
import fr.eni.projetsortie.service.VilleServiceImp;
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
@RequestMapping(path = "/villes")
@Api(value = "Villes")
public class VilleController {
    @Autowired
    private VilleServiceImp villeService;

    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @RequestMapping()
    public List<Ville> getVilles(){
        return this.villeService.list();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Ville> getVille(@PathVariable("id") int id) {
        Ville ville = null;
        ResponseEntity<Ville> response;

        ville = this.villeService.get(id);

        if(ville != null) {
            response = ResponseEntity.ok().body(ville);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Ville> createVille(@RequestBody Ville newVille, UriComponentsBuilder uri) {
        newVille.setId((int) this.villeService.save(newVille));
        URI location =
                uri.path("/villes/")
                        .path(String.valueOf(newVille.getId()))
                        .build()
                        .toUri();
        this.logger.debug("Create ville " + location);
        return ResponseEntity.created(location).body(newVille);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ville> updateVille(@PathVariable("id") int id,
                                                         @RequestBody Ville ville){
        this.villeService.update(ville);
        this.logger.debug("Update ville " + id);
        return ResponseEntity.ok(ville);
    }
}
