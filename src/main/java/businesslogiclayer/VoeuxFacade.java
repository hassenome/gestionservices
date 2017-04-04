/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import persistencelayer.entity.Voeux;
import persistencelayer.entity.Personne;
import persistencelayer.entity.Professeur;

import persistencelayer.entity.Enseignement;

/**
 *
 * @author youssef
 */
public class VoeuxFacade {
      private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    public VoeuxFacade(EntityManagerFactory emf){
        this.emf=emf;
        em=emf.createEntityManager();
        tx=em.getTransaction();
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
        public void create(Voeux d){
        tx.begin();
        em.persist(d);
        tx.commit();
    }
    public void supprimer(Voeux d){
        tx.begin();
        em.remove(d);
        tx.commit();
    }
     public void remove(int id){
        
        tx.begin();
        Voeux e = em.find(Voeux.class,id);
        e.setEnseignement(null);
        em.remove(e);
        tx.commit();
    }
    public void modifier(int id,int num,String cat, int vol,int nbgroupe, int eqtd, Enseignement enseignement, Calendar datevoeux){
        Voeux e = em.find(Voeux.class,id);
        tx.begin();
        e.setNum_voeux(num);
        e.setVolume(vol);
        e.setNbgroupe(nbgroupe);
        e.setEqtd(eqtd);
        e.setEnseignement(enseignement);
        e.setDateVoeux(datevoeux);
        tx.commit();
       
    }
     public void modifierEnseignement(Voeux d ,Enseignement t){
        tx.begin();
        d.setEnseignement(t);
        em.refresh(d);
        tx.commit();
         
    }
     
     public List<Voeux> findAll(){
        tx.begin();
        List<Voeux> liste = em.createQuery("select c from Voeux c").getResultList();
        tx.commit();
        return liste;
    }
      public Voeux findById(int id){
        tx.begin();
        Voeux doc= em.find(Voeux.class, id);
        tx.commit();
        return doc;
    }
      
     
     public List<Voeux> findByName(String nom){
           tx.begin();
           Query q = em.createQuery("select t from Voeux t where t.nom = :nom");
           q.setParameter("nom", nom);
           List<Voeux> c =q.getResultList();
           tx.commit();
           return c;
        }
      public List<Voeux> findByCne(int cne){
           tx.begin();
           Query q = em.createQuery("select t from Voeux t where t.Cne = :cne");
           q.setParameter("cne", cne);
            List<Voeux> li =q.getResultList();
           tx.commit();
           return li;
        }
       public List<Voeux>  findByNins(int num){
           tx.begin();
           Query q = em.createQuery("select t from Voeux t where t.num_inscription = :num");
           q.setParameter("num", num);
            List<Voeux> c =q.getResultList();
           tx.commit();
           return c;
        }
    
     public List<Voeux> find(String value,String param){
       if(param.equalsIgnoreCase("Id")){
           List<Voeux> li = new ArrayList();
           li.add(this.findById(Integer.parseInt(value)));
            return li;       }
       else if(param.equalsIgnoreCase("CNE")){
           return this.findByCne(Integer.parseInt(value));
       }
       else if(param.equalsIgnoreCase("Nom")){
           return this.findByName(value);
       }
       else if(param.equalsIgnoreCase("Num d'inscription")){
           return this.findByNins(Integer.parseInt(value));
       }
        return null;
       
   }
      
     public DefaultTableModel AllDoc(List<Voeux> etu)  {
    DefaultTableModel tableModel = new DefaultTableModel(null, new Object[]{"Id","Ordre","Categorie","Volume","NbGroupes","eqtd","Cours","Date","Proprietaire","Publie"});
    
    for(Voeux E : etu){
        String date = E.getDateVoeux().getTime().toGMTString();        
        if(E.getEnseignement()== null){
            
        tableModel.addRow(new Object[]{E.getId(),E.getNum_voeux(),E.getCategorie(),E.getVolume(),E.getNbgroupe(),E.getEqtd(),"vide",date,E.getOwner().getNom(),E.isIspublic()});
        }
        else{
        	tableModel.addRow(new Object[]{E.getId(),E.getNum_voeux(),E.getCategorie(),E.getVolume(),E.getNbgroupe(),E.getEqtd(),E.getEnseignement().getTitre(),date,E.getOwner().getNom(),E.isIspublic()});
        }         
    }
    
   return tableModel;
      
}
}