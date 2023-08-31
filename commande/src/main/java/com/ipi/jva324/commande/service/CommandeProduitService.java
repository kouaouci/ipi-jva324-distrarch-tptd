package com.ipi.jva324.commande.service;

import com.ipi.jva324.stock.model.ProduitEnStock;
import org.springframework.beans.factory.annotation.Autowired;

public interface CommandeProduitService {
    public ProduitEnStock getProduitEnStock(Long idProduit);
}