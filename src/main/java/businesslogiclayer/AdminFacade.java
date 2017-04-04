/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import persistencelayer.entity.Admin;
import persistencelayer.entity.Enseignement;
import persistencelayer.entity.Personne;
import persistencelayer.entity.Professeur;
import persistencelayer.entity.Voeux;

/**
 *
 * @author hassen
 */
public class AdminFacade {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    public AdminFacade(EntityManagerFactory emf){
        this.emf=emf;
        em=emf.createEntityManager();
        tx=em.getTransaction();
    }
   
    public void create(Personne p){
        tx.begin();
        em.persist(p);
        tx.commit();
    }
     public void remove(int id){
        
        tx.begin();
        Admin e = em.find(Admin.class,id);
        em.remove(e);
        tx.commit();
    }
    public void modifier(int id,String nom,String prenom,int age,String email ){
        Admin e = em.find(Admin.class,id);
        tx.begin();
        e.setNom(nom);
        e.setPrenom(prenom);
        e.setAge(age);
        e.setEmail(email);
        tx.commit();
    }

	public void validerVoeux(Voeux v1) {		
		Enseignement e=em.find(Enseignement.class, v1.getEnseignement().getId());
		tx.begin();		
		e.setProfesseur_Dir((Professeur) v1.getOwner());		
		tx.commit();						
	}

	public void publierVoeux(Voeux v1) {
		Voeux v=em.find(Voeux.class, v1.getId());
		tx.begin();				
		v.setIspublic(true);
		tx.commit();		
	}
	
	public void depublierVoeux(Voeux v1) {
		Voeux v=em.find(Voeux.class, v1.getId());
		tx.begin();				
		v.setIspublic(false);
		tx.commit();		
	}
}
