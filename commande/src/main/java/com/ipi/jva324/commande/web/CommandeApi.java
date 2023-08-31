package com.ipi.jva324.commande.web;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.service.CommandeInvalideException;
import com.ipi.jva324.commande.service.CommandeService;
import com.ipi.jva324.commande.service.StockInsuffisantCommandeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/commandes")
public class CommandeApi {

    @Autowired
    private CommandeService commandeService;



    @PutMapping("{id}")
    public Commande updateCommande(@RequestBody Commande commande) { // without @RequestBody, commande is not filled by auto databinding (though it would be in a web @Controller)
        return commandeService.updateCommande(commande);
    }



    /** que pour tester à ce stade */
    @GetMapping
    public List<Commande> getCommandes() {
        return commandeService.getCommandes();
    }

}
