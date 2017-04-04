package ucTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import businesslogiclayer.AdminFacade;
import businesslogiclayer.EnseignementFacade;
import businesslogiclayer.ProfesseurFacade;
import businesslogiclayer.TrierEnseignements;
import businesslogiclayer.VoeuxFacade;
import persistencelayer.entity.Admin;
import persistencelayer.entity.Enseignement;
import persistencelayer.entity.Professeur;
import persistencelayer.entity.Voeux;

/**
*
* @author hassen
*/


public class UCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==0){
			System.out.println("No args detected, no UC will be executed");			
		}else{
		switch(args[0]){
		case "1":			
			System.out.println("UC—Affecter des enseignements");
			//creation du directeur
			Admin directeur = new Admin("Bross","Farid",50,"Farid@gmail.com");			
			try{
			//Creation d'enseignements	
			System.out.println("Preparation des enseignements");				
			Enseignement E1 = new Enseignement("Genie logiciel","M1","ALMA","S1","CM",1,60);
			Enseignement E2 = new Enseignement("Genie logiciel","M1","ALMA","S1","TD",2,45);
			Enseignement E3 = new Enseignement("Genie logiciel","M1","ALMA","S1","TP",2,20);			
			//creation des enseignants
			System.out.println("Preparation des enseignants");				
			Professeur Fred = new Professeur("Dupont","Fred",40,"fred@gmail.com","@Fred","password","GL");
			Professeur Gaston = new Professeur("Roman","Gaston",38,"gaston@gmail.com","@Gaston","password","Verif&Test");			
			//creation des voeux
			System.out.println("Creations des voeux");				
			Voeux v1= new Voeux(1, "CM", 60, 1, 90, Calendar.getInstance(), E1, Fred);
			Voeux v2= new Voeux(1, "TD", 45, 1, 45, Calendar.getInstance(), E1, Fred);
			Voeux v3= new Voeux(1, "TP", 20, 2, 13, Calendar.getInstance(), E1, Gaston);
			
			List<Voeux> E=new ArrayList<Voeux>();
			E.add(v1);
			E.add(v2);
			E.add(v3);
			System.out.println(E.toString());
			
			//preparation de la persistence 						
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionServices");	        
	        ProfesseurFacade enseignantInstance = new ProfesseurFacade(emf);
	        EnseignementFacade enseignementInstance = new EnseignementFacade(emf);
	        VoeuxFacade voeuxInstance = new VoeuxFacade(emf);
	        AdminFacade directeurInstance = new AdminFacade(emf);	        
	        directeurInstance.create(directeur);
	        enseignementInstance.create(E1);
	        enseignementInstance.create(E2);
	        enseignementInstance.create(E3);	        
	        enseignantInstance.create(Fred);
	        enseignantInstance.create(Gaston);
	        voeuxInstance.create(v1);
	        voeuxInstance.create(v2);
	        voeuxInstance.create(v3);
	        
	        //validation des voeux par le directeur
	        System.out.println("validation des voeux par le directeur");				
	        directeurInstance.validerVoeux(v1);
	        System.out.println(v1.toString());
			}catch(Exception e){e.printStackTrace();}
			break;
			
		case "2":			
			System.out.println("UC—Valider des souhaits et publication des possibles conflits");			
			//creation du directeur
			directeur = new Admin("Robert","Farid",50,"Farid@gmail.com");			
			//Creation d'enseignements
			try{
			System.out.println("Preparation des enseignements");						
			Enseignement E1 = new Enseignement("Verif & Test","M1","ALMA","S1","CM",1,60);
			Enseignement E2 = new Enseignement("Verif & Test","M1","ALMA","S1","TD",2,45);
			Enseignement E3 = new Enseignement("Verif & Test","M1","ALMA","S1","TP",2,20);			
			//creation des enseignants
			System.out.println("Preparation des enseignants");			
			Professeur Fred = new Professeur("Dupont","Fred",40,"fred@gmail.com","@Fred","password","GL");
			Professeur Gaston = new Professeur("Roman","Gaston",38,"gaston@gmail.com","@Gaston","password","Verif&Test");			
			//creation des voeux en conflits
			System.out.println("Creations des voeux en conflits");					
			Voeux v1= new Voeux(1, "CM", 60, 1, 90, Calendar.getInstance(), E1, Fred);
			Voeux v2= new Voeux(1, "TD", 45, 1, 45, Calendar.getInstance(), E1, Fred);
			Voeux v3= new Voeux(1, "TD", 45, 2, 13, Calendar.getInstance(), E1, Gaston);
			//preparation de la persistence 						
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionServices");	        
	        ProfesseurFacade enseignantInstance = new ProfesseurFacade(emf);
	        EnseignementFacade enseignementInstance = new EnseignementFacade(emf);
	        VoeuxFacade voeuxInstance = new VoeuxFacade(emf);
	        AdminFacade directeurInstance = new AdminFacade(emf);	        
	        directeurInstance.create(directeur);
	        enseignementInstance.create(E1);
	        enseignementInstance.create(E2);
	        enseignementInstance.create(E3);	        
	        enseignantInstance.create(Fred);
	        enseignantInstance.create(Gaston);
	        voeuxInstance.create(v1);
	        voeuxInstance.create(v2);
	        voeuxInstance.create(v3);
	        
	        
	        //visualisation des voeux	        
			System.out.println("Voeux non publié : ");
			List<Voeux> E=new ArrayList<Voeux>();
	        E=voeuxInstance.findAll();
	        for(Iterator<Voeux> e = E.iterator(); e.hasNext();){//on affiche uniquement les voeux non publiés
	        	Voeux value=e.next();
	        	if(value.isIspublic()){
	        		e.remove();
	        	}
	        	
	        }
	        System.out.println(E.toString());	
	        
	        
	        //validation des voeux par le directeur
	        System.out.println("validation des voeux par le directeur");
	        directeurInstance.validerVoeux(v1);
	        directeurInstance.validerVoeux(v3);
	        directeurInstance.publierVoeux(v1);
	        directeurInstance.publierVoeux(v3);
	        //pour mettre à jour la valeur Ispublic à l'affichage du test
	        v1.setIspublic(true);
	        v3.setIspublic(true);
	        System.out.println(v1.toString());
	        System.out.println(v3.toString());
	        
	        //analyse des conflits
	        List<Voeux> voeux=new ArrayList<Voeux>();
	        voeux=voeuxInstance.findAll();
	        int indexstart=0;
	        for(Voeux conflit: voeux){
	        	for(int i=indexstart; i<voeux.size() ;i++){
	        		if(conflit.getEnseignement().equals(voeux.get(i).getEnseignement())){
	        			if(conflit.getCategorie().equals(voeux.get(i).getCategorie())){
	        				if((conflit.getVolume()+voeux.get(i).getVolume())>conflit.getEnseignement().getVolume()){
	        					System.out.println("Conflit detecté : "+voeux.get(i).toString()+"\net\n"+voeux.get(i).toString()+"\n");
	        				}
	        			}
	        		}
	        	}
	        	indexstart++;
	        }
	        
	        
			}catch(Exception e){e.printStackTrace();}
			break;
			
		case "3":			
			System.out.println("UC—Analyser des demandes");
			//creation du directeur
			directeur = new Admin("Robert","Farid",50,"Farid@gmail.com");			
			//Creation d'enseignements
			try{
			System.out.println("Preparation des enseignements");						
			Enseignement E1 = new Enseignement("Verif & Test","M1","ALMA","S1","CM",1,60);
			Enseignement E2 = new Enseignement("Verif & Test","M1","ALMA","S1","TD",2,45);
			Enseignement E3 = new Enseignement("Verif & Test","M1","ALMA","S1","TP",2,20);			
			//creation des enseignants
			System.out.println("Preparation des enseignants");			
			Professeur Fred = new Professeur("Dupont","Fred",40,"fred@gmail.com","@Fred","password","GL");
			Professeur Gaston = new Professeur("Roman","Gaston",38,"gaston@gmail.com","@Gaston","password","Verif&Test");			
			//creation des voeux
			System.out.println("Creations des voeux");					
			Voeux v1= new Voeux(1, "CM", 60, 1, 90, Calendar.getInstance(), E1, Fred);
			Voeux v2= new Voeux(1, "TD", 45, 1, 45, Calendar.getInstance(), E1, Fred);
			Voeux v3= new Voeux(1, "TD", 45, 2, 13, Calendar.getInstance(), E1, Gaston);
			//preparation de la persistence 						
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionServices");	        
	        ProfesseurFacade enseignantInstance = new ProfesseurFacade(emf);
	        EnseignementFacade enseignementInstance = new EnseignementFacade(emf);
	        VoeuxFacade voeuxInstance = new VoeuxFacade(emf);
	        AdminFacade directeurInstance = new AdminFacade(emf);	        
	        directeurInstance.create(directeur);
	        enseignementInstance.create(E1);
	        enseignementInstance.create(E2);
	        enseignementInstance.create(E3);	        
	        enseignantInstance.create(Fred);
	        enseignantInstance.create(Gaston);
	        voeuxInstance.create(v1);
	        voeuxInstance.create(v2);
	        voeuxInstance.create(v3);
	        
	        //visualisation des Enseignements
	        //exemple de criteres de tri : par nombre d’heures
			System.out.println("Enseignements : (trié par nombre d'heures)");
			List<Enseignement> E=new ArrayList<Enseignement>();
	        E=enseignementInstance.findAll();
	        TrierEnseignements T = new TrierEnseignements();
	        Collections.sort(E,T);
	        for(Enseignement e : E){
	        	System.out.println(e.getTitre()+" "+e.getAnnee_etude()+" "+e.getParcours()+" "+e.getSemestre()+" "+"volume : "+e.getVolume());
	        }
	        
	        //analyse statistique des volumes horaires (voeux et enseignements)
	        List<Voeux> voeux=new ArrayList<Voeux>();
	        voeux=voeuxInstance.findAll();
	        int totalVolumeVoeux=0;
	        int totalVolumeEnseignement=0;
        	for(Voeux testVoeux : voeux){
	        		totalVolumeVoeux=testVoeux.getVolume();
	        		totalVolumeEnseignement=testVoeux.getEnseignement().getVolume();
	        }
        	System.out.println("Pour l'enseignement :"+E1.getTitre());
        	System.out.println("Volume total des heures CM, TD et TP disponible :"+totalVolumeEnseignement);
        	System.out.println("Volume total des heures des demandes :"+totalVolumeVoeux);
	        //validation des voeux par le directeur après analyse des conflits
	        System.out.println("validation des voeux par le directeur");
	        directeurInstance.validerVoeux(v1);
	        directeurInstance.validerVoeux(v3);
	        System.out.println(v1.toString());
	        System.out.println(v3.toString());
			}catch(Exception e){e.printStackTrace();}
			break;			
			
		case "4":
			System.out.println("UC—Émettre un souhait");
			try{
			//Creation d'enseignements	
			System.out.println("Preparation des enseignements");				
			Enseignement E1 = new Enseignement("Genie logiciel","M1","ALMA","S1","CM",1,60);
			Enseignement E2 = new Enseignement("Genie logiciel","M1","ALMA","S1","TD",2,45);
			//creation des enseignants
			System.out.println("Preparation de l'enseignant");				
			Professeur Richard = new Professeur("Huffman","Richard",40,"fred@gmail.com","@Fred","password","GL");			
			//preparation de la persistence 						
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionServices");	        
	        ProfesseurFacade enseignantInstance = new ProfesseurFacade(emf);
	        EnseignementFacade enseignementInstance = new EnseignementFacade(emf);
	        VoeuxFacade voeuxInstance = new VoeuxFacade(emf);	        
	        enseignementInstance.create(E1);
	        enseignementInstance.create(E2);;	        
	        enseignantInstance.create(Richard);
	        //visualisation des Enseignements	        
			System.out.println("Enseignements disponibles : ");
			List<Enseignement> E=new ArrayList<Enseignement>();
	        E=enseignementInstance.findAll();
	        for(Iterator<Enseignement> e = E.iterator(); e.hasNext();){//on affiche uniquement les enseignements non affectés
	        	Enseignement value=e.next();
	        	if(value.getProfesseur_Dir()!=null){
	        		e.remove();
	        	}
	        	
	        }
	        for(Enseignement e : E){
	        	System.out.println(e.getTitre()+" "+e.getAnnee_etude()+" "+e.getParcours()+" "+e.getSemestre()+" "+"volume : "+e.getVolume());
	        }
	        
	        //creation du voeux
			System.out.println("Creations du voeux");
			E=enseignementInstance.findByName(E1.getTitre());
			//recuperation de l'enseignement E1 à choisir de le voeux
			Enseignement cible;
			for(Enseignement e : E){
				if((e.getTitre()==E1.getTitre())&&(e.getAnnee_etude()==E1.getAnnee_etude())&&(e.getType()==E1.getType())&&(e.getSemestre()==E1.getSemestre())){
					cible=e;
					if(cible.getProfesseur_Dir()==null){ //si enseignement non affecté
						Voeux v1= new Voeux(1, "CM", 60, 1, 90, Calendar.getInstance(), E1, Richard); //ordre du voeux 1
						System.out.print(v1.toString());
						voeuxInstance.create(v1);
						break;
				}
			}			
			}	        	        
			}catch(Exception e){e.printStackTrace();}
			break;
			
		case "5":
			System.out.println("UC—Publier des souhaits");
			//creation du directeur
			directeur = new Admin("Bross","Farid",50,"Farid@gmail.com");			
			try{
			//Creation d'enseignements	
			System.out.println("Preparation des enseignements");				
			Enseignement E1 = new Enseignement("Genie logiciel","M1","ALMA","S1","CM",1,60);
			Enseignement E2 = new Enseignement("Genie logiciel","M1","ALMA","S1","TD",2,45);
			Enseignement E3 = new Enseignement("Genie logiciel","M1","ALMA","S1","TP",2,20);			
			//creation des enseignants
			System.out.println("Preparation de l'enseignant");				
			Professeur Richard = new Professeur("Huffman","Richard",40,"fred@gmail.com","@Fred","password","GL");
			//creation des voeux de l'enseignant
			System.out.println("Creations des voeux de l'enseignant");				
			Voeux v1= new Voeux(1, "CM", 60, 1, 90, Calendar.getInstance(), E1, Richard);
			Voeux v2= new Voeux(1, "TD", 45, 1, 45, Calendar.getInstance(), E1, Richard);
			Voeux v3= new Voeux(1, "TP", 20, 2, 13, Calendar.getInstance(), E1, Richard);
			//preparation de la persistence 						
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionServices");	        
	        ProfesseurFacade enseignantInstance = new ProfesseurFacade(emf);
	        EnseignementFacade enseignementInstance = new EnseignementFacade(emf);
	        VoeuxFacade voeuxInstance = new VoeuxFacade(emf);
	        AdminFacade directeurInstance = new AdminFacade(emf);	        
	        directeurInstance.create(directeur);
	        enseignementInstance.create(E1);
	        enseignementInstance.create(E2);
	        enseignementInstance.create(E3);	        
	        enseignantInstance.create(Richard);
	        voeuxInstance.create(v1);
	        voeuxInstance.create(v2);
	        voeuxInstance.create(v3);
	        
     	    //visualisation des voeux	        
			System.out.println("Voeuxs de l'enseignement non publié : ");
			List<Voeux> E=new ArrayList<Voeux>();
	        E=voeuxInstance.findAll();
	        for(Iterator<Voeux> e = E.iterator(); e.hasNext();){//on affiche uniquement les voeux non publiés
	        	Voeux value=e.next();
	        	if(value.isIspublic()){
	        		e.remove();
	        	}
	        	
	        }
	        System.out.println(E.toString());	        
	        //publication des voeux par le directeur
	        System.out.println("publication des voeuxs par le directeur");				
	        directeurInstance.publierVoeux(v1);
	        directeurInstance.publierVoeux(v2);	        
	        System.out.println("Voeuxs de l'enseignant publié par le directeur : ");
	        //pour mettre à jour la valeur Ispublic à l'affichage du test
	        v1.setIspublic(true);
	        v2.setIspublic(true);
	        System.out.println(v1.toString());				
	        System.out.println(v2.toString());
			}catch(Exception e){e.printStackTrace();}
			break;
			
			
			
			
			
			
			
		case "6":
			System.out.println("UC—Consulter des enseignements");
			try{
			//Creation d'enseignements	
			System.out.println("Preparation des enseignements");				
			Enseignement E1 = new Enseignement("Genie logiciel","M1","ALMA","S1","CM",1,60);
			Enseignement E2 = new Enseignement("Genie logiciel","M1","ALMA","S1","TD",2,45);
			Enseignement E3 = new Enseignement("Genie logiciel","M1","ALMA","S1","TP",2,20);			
			//creation des enseignants
			System.out.println("Preparation de l'enseignant");				
			Professeur Richard = new Professeur("Huffman","Richard",40,"fred@gmail.com","@Fred","password","GL");
			//preparation de la persistence 						
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionServices");	        
	        ProfesseurFacade enseignantInstance = new ProfesseurFacade(emf);
	        EnseignementFacade enseignementInstance = new EnseignementFacade(emf);
	        enseignementInstance.create(E1);
	        enseignementInstance.create(E2);
	        enseignementInstance.create(E3);	        
	        enseignantInstance.create(Richard);	        
	        //exemple de criteres de tri : par nombre d’heures
	        System.out.println("Enseignements : (trié par nombre d'heures)");
			List<Enseignement> E=new ArrayList<Enseignement>();
	        E=enseignementInstance.findAll();
	        TrierEnseignements T = new TrierEnseignements();
	        Collections.sort(E,T);
	        for(Enseignement e : E){
	        	System.out.println(e.getTitre()+" "+e.getAnnee_etude()+" "+e.getParcours()+" "+e.getSemestre()+" "+"volume : "+e.getVolume());
	        }	        
			}catch(Exception e){e.printStackTrace();}
			break;
																											
			default:
				System.out.println("undefined argument for UC");
				break;
		
		}
		
		}

	}

}
