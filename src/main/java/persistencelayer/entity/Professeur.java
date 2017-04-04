/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencelayer.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author hassen
 */
	@Entity
	public class Professeur extends Personne {
   
    @Column
    protected String domaine;
    
    @OneToMany(mappedBy = "professeur_Dir",cascade=CascadeType.REMOVE)
    private Collection <Enseignement> enseignements;
    
    public Professeur(){}
   
   public Professeur(String nom,String prenom,int age,String email,String login, String password ,String domaine){       
       this.nom=nom;
        this.prenom=prenom;
        this.age=age;
        this.email=email;
        this.login=login;
        this.password=password;
        this.domaine=domaine;
   }
   

   

    @Override
public String toString() {
	return "Professeur [domaine=" + domaine + ", enseignements=" + enseignements + "]";
}

	/**
     * @return the domaine
     */
    public String getDomaine() {
        return domaine;
    }

    /**
     * @param domaine the domaine to set
     */
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    /**
     * @return the enseignements
     */
    public Collection <Enseignement> getEnseignements() {
        return enseignements;
    }

    /**
     * @param enseignements the enseignements to set
     */
    public void setEnseignements(Collection <Enseignement> enseignements) {
        this.enseignements = enseignements;
    }
    
}
