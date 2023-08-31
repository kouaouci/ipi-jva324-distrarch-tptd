package com.ipi.jva324.commande.service;

import com.ipi.jva324.commande.service.CommandeProduitService;
import com.ipi.jva324.stock.model.ProduitEnStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Primary
@Component
public class CommandeProduitServiceRESTHALImp  implements CommandeProduitService {
    @Value ("${ecommerce.apiserver.url:http://localhost:8282/}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;
    // Pour s'en servir dans une class annotée @Component ou @Service... :
    @Autowired @Qualifier("springDataRestTemplate")
    private RestTemplate springDataRestTemplate;
    @Override
    public ProduitEnStock getProduitEnStock(Long id) {
        // GET
        ProduitEnStock p = springDataRestTemplate.getForObject(url + "api/data-rest/produitEnStocks", ProduitEnStock.class);
        return p;
    }
}
