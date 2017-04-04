/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencelayer.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import sun.security.util.Password;

/**
 *
 * @author hassen
 */
@Entity
public class Admin extends Personne {
    
   @Column
   private int num_admin;

     private static int counter = 0;
   
     @OneToMany
     private Collection <Professeur> professeurs;
     
     @OneToMany
     private Collection<Voeux> voeuxs;
     
     public Admin(){this.num_admin = counter++;}
   
   public Admin(String nom,String prenom,int age,String email){
          this.nom=nom;
        this.prenom=prenom;
        this.age=age;
        this.email=email;
        this.login="admin";
        this.password="admin";
        this.num_admin = counter++;
   }
   
   @Override
    public String toString() {
        return "Admin : [ num_admin=" + this.getNum_admin() + " Nom:  "+this.getNom()+" , Prenom: "+this.getPrenom()+" , age : "+this.getAge()+" , Email : "+this.getEmail()+" ]";
    }

    /**
     * @return the num_admin
     */
    public int getNum_admin() {
        return num_admin;
    }

    /**
     * @return the professeurs
     */
    public Collection <Professeur> getProfesseurs() {
        return professeurs;
    }

    /**
     * @param professeurs the professeurs to set
     */
    public void setProfesseurs(Collection <Professeur> professeurs) {
        this.professeurs = professeurs;
    }

    /**
     * @return the voeuxs
     */
    public Collection<Voeux> getVoeuxs() {
        return voeuxs;
    }

    /**
     * @param voeuxs the voeuxs to set
     */
    public void setVoeuxs(Collection<Voeux> voeuxs) {
        this.voeuxs = voeuxs;
    }
    
}
