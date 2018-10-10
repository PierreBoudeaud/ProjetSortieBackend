package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Lieu;
import fr.eni.projetsortie.service.LieuServiceImp;
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
@RequestMapping(path = "/lieux")
@Api(value = "Lieux")
public class LieuController {
    @Autowired
    private LieuServiceImp lieuService;

    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @RequestMapping()
    public List<Lieu> getLieux(){
        return this.lieuService.list();
    }

    @RequestMapping(params = "ville")
    public List<Lieu> getLieuxByVille(@RequestParam("ville") int ville){
        return this.lieuService.listByVille(ville);
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Lieu> getLieu(@PathVariable("id") int id) {
        Lieu lieu = null;
        ResponseEntity<Lieu> response;

        lieu = this.lieuService.get(id);

        if(lieu != null) {
            response = ResponseEntity.ok().body(lieu);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Lieu> createLieu(@RequestBody Lieu newLieu,
                                                         UriComponentsBuilder uri) {
        newLieu.setId( (int) this.lieuService.save(newLieu));
        URI location =
                uri.path("/lieux/")
                        .path(String.valueOf(newLieu.getId()))
                        .build()
                        .toUri();
        this.logger.debug("Create lieu " + location);
        return ResponseEntity.created(location).body(newLieu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Lieu> updateLieu(@PathVariable("id") int id,
                                                         @RequestBody Lieu lieu){
        this.lieuService.update(lieu);
        this.logger.debug("Update lieu " + id);
        return ResponseEntity.ok(lieu);
    }
}
