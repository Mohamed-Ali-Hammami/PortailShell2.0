package com.tn.shell.model.lavage;

import java.math.BigDecimal;

import javax.persistence.*;

import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Statut;

@Entity
@Table(name = "Affectationhuile")
public class AffectationHuile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produitid")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "modelid")
    private Model model;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.ACTIF;

    private BigDecimal metrage;
    private BigDecimal nbvidange;

    // ================= GETTERS / SETTERS =================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public BigDecimal getMetrage() {
        return metrage;
    }

    public void setMetrage(BigDecimal metrage) {
        this.metrage = metrage;
    }

    public BigDecimal getNbvidange() {
        return nbvidange;
    }

    public void setNbvidange(BigDecimal nbvidange) {
        this.nbvidange = nbvidange;
    }
}