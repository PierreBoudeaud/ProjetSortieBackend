package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Sortie;
import fr.eni.projetsortie.service.SortieServiceImp;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sorties")
@Api(value = "Sorties")
public class SortieController {
    @Autowired
    private SortieServiceImp sortieService;

    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @RequestMapping()
    public List<Sortie> getSorties(){
        return this.sortieService.list();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Sortie> getSortie(@PathVariable("id") int id){
        Sortie sortie = null;
        ResponseEntity<Sortie> response;

        sortie = this.sortieService.get(id);

        if(sortie != null){
            response = ResponseEntity.ok().body(sortie);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @RequestMapping()
    public ResponseEntity<Sortie> createSortie (@RequestBody Sortie sortie){
        this.sortieService.save(sortie);
        this.logger.debug("Create sortie " + sortie.getId());
        return ResponseEntity.ok(sortie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sortie> updateSortie (@PathVariable("id") int id,
                                                         @RequestBody Sortie sortie){
        this.sortieService.update(sortie);
        this.logger.debug("Update sortie " + id);
        return ResponseEntity.ok(sortie);
    }
}
