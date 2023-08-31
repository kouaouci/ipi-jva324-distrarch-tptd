package com.ipi.jva324.commande.service;

import com.ipi.jva324.commande.service.CommandeProduitService;
import com.ipi.jva324.stock.model.ProduitEnStock;
//import com.ipi.jva324.stock.service.ProduitService;
import com.ipi.jva324.stock.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommandeProduitServiceLLocalImpl  implements CommandeProduitService {


   @Autowired
    private ProduitService produitService;

    @Override
    public ProduitEnStock getProduitEnStock(Long idProduit) {
        return  produitService.getProduit (idProduit);
    }
}
