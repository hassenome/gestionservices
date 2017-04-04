/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
 * @author nadhir
 */
public class EnseignementFacade {
   private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    public EnseignementFacade(EntityManagerFactory emf){
        this.emf=emf;
        em=emf.createEntityManager();
        tx=em.getTransaction();
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
        public void create(Enseignement t){
        tx.begin();
        em.persist(t);
        tx.commit();
    }
    public void supprimer(Enseignement t){
        tx.begin();
        em.remove(t);
        tx.commit();
    }
     public void remove(int id){
        
        tx.begin();
        Enseignement e = em.find(Enseignement.class,id);
        e=em.merge(e);
        em.remove(e);
        tx.commit();
    }
    public void modifier(int id,String titre, String annee_etude, String parcours, String semestre, String type,
			int nbgroupe, int volume){
        Enseignement e = em.find(Enseignement.class,id);
        tx.begin();
        e.setTitre(titre);
        e.setSemestre(semestre);
        e.setAnnee_etude(annee_etude);
        e.setParcours(parcours);
        e.setType(type);
        e.setNbgroupe(nbgroupe);
        e.setVolume(volume);
        tx.commit();
        em.merge(e);
    }
    
      
     public void AffecterDir(String nom, Enseignement t){
         tx.begin();
          Query q  = em.createQuery("select p from Professeur p where p.nom= :nom");
        q.setParameter("nom", nom);
        Professeur p = (Professeur)q.getSingleResult();
         Collection <Enseignement> enseignements=p.getEnseignements();
         enseignements.add(t);
         p.setEnseignements(enseignements);
         t.setProfesseur_Dir(p);
    tx.commit();}
     
     
     public void RemoveDir(Enseignement t){
         tx.begin();                          
         t.setProfesseur_Dir(null);
    tx.commit();}
     
     
     public List<String> nomEnseignements(){
       tx.begin();
    List<String> listenom = em.createQuery("select p.titre from Enseignement p").getResultList();
    tx.commit();
    return listenom;
    }
      
      public List<String> nomEnseignements(List<Enseignement>enseignements){
    List<String> listenom = new ArrayList();
    Iterator<Enseignement> it = enseignements.listIterator();
    while(it.hasNext()){
        listenom.add(it.next().getTitre());
    }
    return listenom;
    }
     
      public List<String> nomDomaine(){
       tx.begin();
    List<String> listedomaine = em.createQuery("select distinct t.domaine from Enseignement t").getResultList();
    tx.commit();
    return listedomaine;
    }
      public Boolean estPresente(String presente){
          if(presente.equalsIgnoreCase("oui")){
              return true;
          }
          else{
              return false;
          }
      }
      public int retournerId(String titre){
       Query q  = em.createQuery("select p.id from Enseignement p where p.titre= :titre");
        q.setParameter("titre", titre);
        int id= (int) q.getSingleResult();
        return id;
      }
     
     
      public List<Enseignement> findAll(){
        tx.begin();
        List<Enseignement> liste = em.createQuery("select c from Enseignement c").getResultList();
        tx.commit();
        return liste;
    }
       public Enseignement findById(int id){
        tx.begin();
        Enseignement enseignement= em.find(Enseignement.class, id);
        tx.commit();
        return enseignement;
    }
       public Enseignement findByNom(String titre){
           tx.begin();
           Query q = em.createQuery("select t from Enseignement t where t.titre = :titre");
           q.setParameter("titre", titre);
           Enseignement t =(Enseignement)q.getSingleResult();
           tx.commit();
           return t;
        }
         public List<Integer> enseignementsId(int id){
       tx.begin();
       Query q  = em.createQuery("select t.id from Enseignement t join t.candidats c where c.id= :id ");
        q.setParameter("id", id);
        List<Integer> listeid= q.getResultList();
         tx.commit();
    return listeid;
    }
    public List<Enseignement> listeEnseignements(List<Integer> id){
             List<Enseignement> liste = new ArrayList();
             for(int i = 0;i<id.size();i++){
                 Enseignement t = this.findById(id.get(i));
                 liste.add(t);
             }
             return liste;
         }
    
      public List<Enseignement> findByDomaine(String domaine) {
        tx.begin();
           Query q = em.createQuery("select t from Enseignement t where t.domaine = :domaine");
           q.setParameter("domaine", domaine);
            List<Enseignement> c =q.getResultList();
           tx.commit();
           return c;
    }

    public List<Enseignement> findByName(String titre) {
            tx.begin();
           Query q = em.createQuery("select t from Enseignement t where t.titre = :titre");
           q.setParameter("titre", titre);
            List<Enseignement> c =q.getResultList();
           tx.commit();
           return c;
    }

    public List<Enseignement> findByProf(String nom) {
     tx.begin();
           Query q = em.createQuery("select t from Enseignement t join t.professeur_Dir p where p.nom= :nom ");
           q.setParameter("nom", nom);
            List<Enseignement> c =q.getResultList();
           tx.commit();
           return c;
    }

    
    public List<Enseignement> find(String value,String param){
       if(param.equalsIgnoreCase("Id")){
           List<Enseignement> li = new ArrayList();
           li.add(this.findById(Integer.parseInt(value)));
            return li;       }
       else if(param.equalsIgnoreCase("Domaine")){
           return this.findByDomaine(value);
       }
       else if(param.equalsIgnoreCase("Titre")){
           return this.findByName(value);
       }
       else if(param.equalsIgnoreCase("Professeur Directeur")){
           return this.findByProf(value);
       }
        return null;
       
   }
         
         
     public DefaultTableModel AllEns(List<Enseignement> etu)  {
    DefaultTableModel tableModel = new DefaultTableModel(null, new Object[]{"ID","Cours","annee_etude","parcours","semestre","type","nbgroupe","volume","Professeur"});
    for(Enseignement E : etu){
        if( E.getProfesseur_Dir()== null){
                tableModel.addRow(new Object[]{E.getId(),E.getTitre(),E.getAnnee_etude(),E.getParcours(),E.getSemestre(),E.getType(),E.getNbgroupe(),E.getVolume(),"vide"});
        }
        else{
        	tableModel.addRow(new Object[]{E.getId(),E.getTitre(),E.getAnnee_etude(),E.getParcours(),E.getSemestre(),E.getType(),E.getNbgroupe(),E.getVolume(),E.getProfesseur_Dir().getNom()});
            }
        }
    
    
   return tableModel;
      
}
     

}