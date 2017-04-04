/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import persistencelayer.entity.Personne;
import persistencelayer.entity.Professeur;
import persistencelayer.entity.Enseignement;

/**
 *
 * @author hassen
 */
public class ProfesseurFacade {
      private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    public ProfesseurFacade(EntityManagerFactory emf){
        this.emf=emf;
        em=emf.createEntityManager();
        tx=em.getTransaction();
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
        public void create(Personne p){
        tx.begin();
        em.persist(p);
        tx.commit();
    }
    public void supprimer(Personne p){
        tx.begin();
        em.remove(p);
        tx.commit();
    }
     public void remove(int id){
        
        tx.begin();
        Professeur e = em.find(Professeur.class,id);
        em.remove(e);
        tx.commit();
    }
    public void modifier(int id,String nom,String prenom,int age,String email,String login, String password ,String domaine){
        Professeur e = em.find(Professeur.class,id);
        tx.begin();        
        e.setNom(nom);
        e.setPrenom(prenom);
        e.setAge(age);
        e.setEmail(email);
        e.setLogin(login);
        e.setPassword(password);
        e.setDomaine(domaine);
        
        tx.commit();
        
    }
   public List<Enseignement> ListeEnseignements(Integer id){
       Professeur p = em.find(Professeur.class, id);
               Query query= em.createQuery("select t from Enseignement t where t.professeur_Dir =:p");
        query.setParameter("p", p);
        List <Enseignement> liste = new ArrayList<Enseignement>();
        liste = query.getResultList();
        return liste;
   }
   public List<String> nomProfs(){
       tx.begin();
    List<String> listenom = em.createQuery("select p.nom from Professeur p").getResultList();
    tx.commit();
    return listenom;
    }
    public Professeur findById(int id){
        tx.begin();
     Professeur prof= em.find(Professeur.class, id);
        tx.commit();
        return prof;
    }
   public List<Professeur> findAll() {
            tx.begin();
		List<Professeur> professeurs = em.createQuery(
				"select p from Professeur p").getResultList();
		tx.commit();
                return professeurs;
               
	}
    public List<Professeur> findByCode(int code) {
            tx.begin();
		Query q= em.createQuery("select p from Professeur p where p.code_prof= :code");
                q.setParameter("code",code);
                List<Professeur> professeurs =q.getResultList();
		tx.commit();
                return professeurs;
               
	}
     public List<Professeur> findByNom(String nom) {
            tx.begin();
		Query q= em.createQuery("select p from Professeur p where p.nom= :nom");
                q.setParameter("nom",nom);
                List<Professeur> professeurs =q.getResultList();
		tx.commit();
                return professeurs;
               
	}
      public List<Professeur> findByDomaine(String domaine) {
            tx.begin();
		Query q= em.createQuery("select p from Professeur p where p.domaine= :domaine");
                q.setParameter("domaine",domaine);
                List<Professeur> professeurs =q.getResultList();
		tx.commit();
                return professeurs;
               
	}
      
      
      public Professeur findByLogin(String login) {
          tx.begin();
		Query q= em.createQuery("select p from Professeur p where p.login= :login");
              q.setParameter("login",login);
              List<Professeur> professeurs =q.getResultList();
		tx.commit();
              return professeurs.get(0);             
	}
   
   public List<Professeur> find(String value,String param){
       if(param.equalsIgnoreCase("Id")){
           List<Professeur> li = new ArrayList();
           li.add(this.findById(Integer.parseInt(value)));
            return li;       }
       else if(param.equalsIgnoreCase("code Prof")){
           return this.findByCode(Integer.parseInt(value));
       }
       else if(param.equalsIgnoreCase("Nom")){
           return this.findByNom(value);
       }
       else if(param.equalsIgnoreCase("Domaine")){
           return this.findByDomaine(value);
       }
       else return null;
       
   }
    public DefaultTableModel AllProf(List<Professeur> prof)  {
    DefaultTableModel tableModel = new DefaultTableModel(null, new Object[]{"id","Nom","Prénom","Age","Domaine"});
    for(Professeur P : prof){
        tableModel.addRow(new Object[]{P.getId(),P.getNom(),P.getPrenom(),P.getAge(),P.getDomaine()});
    }
    
   return tableModel;
      }
}
