package fr.eni.projetsortie.controller;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Site;
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
@RequestMapping("/sites")
@Api(value = "Sites")
public class SiteController {
    private final static Logger log = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @Autowired
    private SiteServiceImp siteService;

    @RequestMapping()
    public List<Site> getSites() {
        return this.siteService.list();
    }

    @RequestMapping("{id}")
    public ResponseEntity<Site> getSite(@PathVariable("id") int id) {
        Site site = null;
        ResponseEntity<Site> response;
        site = this.siteService.get(id);
        if(site != null){
            response = ResponseEntity.ok(site);
            log.debug("Site found" + site.toString());
        }
        else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Site> createSite(@RequestBody() Site newSite, UriComponentsBuilder uri) {
        newSite.setId(this.siteService.save(newSite));
        URI location =
                uri.path("/sites/")
                        .path(String.valueOf(newSite.getId()))
                        .build()
                        .toUri();
        return ResponseEntity.created(location).body(newSite);
    }


}
