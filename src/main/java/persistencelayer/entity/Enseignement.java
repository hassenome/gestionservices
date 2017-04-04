/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencelayer.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author lemcirdi
 */
@Entity
public class Enseignement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    
    @Column
    protected String titre;
    
    @Column
    protected String annee_etude;
    
    @Column
    protected String parcours;
        
    @Column
    protected String semestre;
    
    @Column
    protected String type;
    
    @Column
    protected int nbgroupe;
    
    @Column
    protected int volume;
        
    @ManyToOne
    private Professeur professeur_Dir;
    
    
    public Enseignement(){}        	    

    
    public Enseignement(String titre, String annee_etude, String parcours, String semestre, String type,
			int nbgroupe, int volume) {		
		this.titre = titre;
		this.annee_etude = annee_etude;
		this.parcours = parcours;
		this.semestre = semestre;
		this.type = type;
		this.nbgroupe = nbgroupe;
		this.volume = volume;
	}





	public Integer getId() {
		return id;
	}







	public void setId(Integer id) {
		this.id = id;
	}







	public String getTitre() {
		return titre;
	}







	public void setTitre(String titre) {
		this.titre = titre;
	}







	public String getAnnee_etude() {
		return annee_etude;
	}







	public void setAnnee_etude(String annee_etude) {
		this.annee_etude = annee_etude;
	}







	public String getParcours() {
		return parcours;
	}







	public void setParcours(String parcours) {
		this.parcours = parcours;
	}







	public String getSemestre() {
		return semestre;
	}







	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}







	public String getType() {
		return type;
	}







	public void setType(String type) {
		this.type = type;
	}







	public int getNbgroupe() {
		return nbgroupe;
	}







	public void setNbgroupe(int nbgroupe) {
		this.nbgroupe = nbgroupe;
	}







	public int getVolume() {
		return volume;
	}







	public void setVolume(int volume) {
		this.volume = volume;
	}







	/**
     * @return the professeur_Dir
     */
    public Professeur getProfesseur_Dir() {
        return professeur_Dir;
    }

    /**
     * @param professeur_Dir the professeur_Dir to set
     */
    public void setProfesseur_Dir(Professeur professeur_Dir) {
        this.professeur_Dir = professeur_Dir;
    }
    


    
}