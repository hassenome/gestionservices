package businesslogiclayer;

import java.util.Comparator;

import persistencelayer.entity.Enseignement;

public class TrierEnseignements implements Comparator <Enseignement> {
	
	

	public TrierEnseignements() {
		
	}

	@Override
	public int compare(Enseignement o1, Enseignement o2) {
		// TODO Auto-generated method stub
		if(o1.getVolume()==o2.getVolume()){
		return 0;
	}else{
		if(o1.getVolume()<o2.getVolume()){
			return -1;
		}else{
			return 1;
		}
	}
	}

}
