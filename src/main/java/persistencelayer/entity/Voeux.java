/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencelayer.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nadhir
 */
@Entity
public class Voeux implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
	
    @Column
    protected int num_voeux;
    
    @Column
    protected String categorie;
    
    @Column
    protected int volume;
    
    @Column
    protected int nbgroupe;
    
    @Column
    protected int eqtd;
    
    @Temporal(TemporalType.DATE)
    private Calendar dateVoeux;
    
    @OneToOne
    private Enseignement enseignement;
    
    @Column
    protected Personne owner;
    
    @Column
    protected boolean ispublic;
    
    public Voeux (){}       
    
    

	public Voeux(int num_voeux, String categorie, int volume, int nbgroupe, int eqtd, Calendar dateVoeux,
			Enseignement enseignement, Personne owner) {
		super();
		this.id = id;
		this.num_voeux = num_voeux;
		this.categorie = categorie;
		this.volume = volume;
		this.nbgroupe = nbgroupe;
		this.eqtd = eqtd;
		this.dateVoeux = dateVoeux;
		this.enseignement = enseignement;
		this.owner = owner;
		this.ispublic=false;
	}



	public int getNbgroupe() {
		return nbgroupe;
	}



	public void setNbgroupe(int nbgroupe) {
		this.nbgroupe = nbgroupe;
	}



	public int getEqtd() {
		return eqtd;
	}



	public void setEqtd(int eqtd) {
		this.eqtd = eqtd;
	}



	
		

    @Override
	public String toString() {
		return "Voeux [id=" + id + ", num_voeux=" + num_voeux + ", categorie=" + categorie + ", volume=" + volume
				+ ", nbgroupe=" + nbgroupe + ", eqtd=" + eqtd + ", dateVoeux=" + dateVoeux + ", enseignement="
				+ enseignement + ", owner=" + owner + ", ispublic=" + ispublic + "]";
	}



	/**
     * @return the num_voeux
     */
    public int getNum_voeux() {
        return num_voeux;
    }

    /**
     * @param num_voeux the num_voeux to set
     */
    public void setNum_voeux(int num_inscription) {
        this.num_voeux = num_inscription;
    }

    /**
     * @return the enseignement
     */
    public Enseignement getEnseignement() {
        return enseignement;
    }

    /**
     * @param enseignement the enseignement to set
     */
    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

    /**
     * @return the dateSoutenance
     */
    public Calendar getDateVoeux() {
        return dateVoeux;
    }

    /**
     * @param dateSoutenance the dateSoutenance to set
     */
    public void setDateVoeux(Calendar dateInscrption) {
        this.dateVoeux = dateVoeux;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public int getVolume() {
		return volume;
	}



	public void setVolume(int volume) {
		this.volume = volume;
	}



	public Personne getOwner() {
		return owner;
	}



	public void setOwner(Personne owner) {
		this.owner = owner;
	}



	public boolean isIspublic() {
		return ispublic;
	}



	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
    
    
   
   
    
}