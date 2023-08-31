package com.ipi.jva324.stock;

import com.ipi.jva324.Jva324Application;


import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.model.ReceptionDeProduit;
import com.ipi.jva324.stock.repository.ProduitEnStockRepository;
import com.ipi.jva324.stock.repository.ReceptionDeProduitRepository;
import com.ipi.jva324.stock.service.ProduitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class) // Junit 4 : @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Jva324Application.class)
public class StockTest {

    /**
     * TODO rm, pas utile ici
     */
    @Value(value = "${local.server.port}")
    private int port;


    @Autowired
    private ProduitEnStockRepository produitEnStockRepository;

    /**
     * aide pour les tests
     */
    @Autowired
    private ProduitService produitService;
    @Autowired
    private ReceptionDeProduitRepository receptionDeProduitRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testStockSuffisant() {
        // produit créé en init
        ProduitEnStock p1 = produitService.getProduits ().get ( 0 );
        // NB. pas possible de plutôt créer un nouveau produit en test car la tx n'est pas committée
        // (à moins d'appeler en REST ou par un service fermant la tx déclarativement ou explicitement)
        //ProduitEnStock p1 = produitService.createProduit(new ProduitEnStock("Google Pixel 7", null, 2));
        //Object res1 = produitEnStockRepository.findAll();//TODO rm
        Assertions.assertTrue ( p1.getQuantiteDisponible () > 0 );  //TODO rm
        Assertions.assertTrue ( p1.getQuantiteDisponible () >= 1 );  //TODO rm

        ProduitEnStock produit = produitService.createProduit ( new ProduitEnStock ( "produit", null, 2 ) );
        assertNotNull ( produit, "produit non null" );


    }

    // tester la methode reception produit
    @Test
    public void testReceptionProduit() {
        // Créez un objet ReceptionDeProduit pour les tests
        ReceptionDeProduit receptionDeProduit = new ReceptionDeProduit();
        receptionDeProduit.setIdProduit(1L);
        receptionDeProduit.setQuantiteRecue(5);
        receptionDeProduit.setTimestamp(null);

        // Appelez la méthode à tester
        ReceptionDeProduit resultat = produitService.recoitProduit(receptionDeProduit);

        // Vérifiez que la méthode a correctement modifié les quantités et sauvegardé les données
        ProduitEnStock produitEnStockApresReception = produitEnStockRepository.findById(1L).orElse(null);
        assertNotNull(produitEnStockApresReception, "ProduitEnStock doit être présent en base de données");
        assertEquals(15, produitEnStockApresReception.getQuantiteDisponible(), "Quantité incorrecte après réception");
        assertNotNull(resultat.getTimestamp(), "Le timestamp doit être défini");
    }

}
