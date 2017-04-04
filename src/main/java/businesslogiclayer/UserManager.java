package businesslogiclayer;
import persistencelayer.entity.Personne;

public class UserManager {
	
	/**
	 *
	 * @author anas
	 */
	
	private static Personne user;
	private static boolean connected;
	
	public UserManager(Personne usr){
		UserManager.user=usr;
	}

	public static Personne getUser() {
		return user;
	}

	public static void setUser(Personne user) {
		UserManager.user = user;
	}

	public static boolean isConnected() {
		return connected;
	}

	public static void setConnected(boolean connected) {
		UserManager.connected = connected;
	};
	
	
	
	
	

}
