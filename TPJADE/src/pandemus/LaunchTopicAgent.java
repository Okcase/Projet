package pandemus;

import pandemus.gui.Fenetre;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.core.Runtime;

public class LaunchTopicAgent {

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
		
		boolean createdAgent = false;
		
		//services et agents à lancer par JADE
		Properties prop = new ExtendedProperties();
		
		// autoriser la fenêtre de contrôle
		prop.setProperty(Profile.GUI, "true");
		
		// activer le 'TopicManagementService'
		prop.setProperty(Profile.SERVICES, "jade.core.messaging.TopicManagementService; jade.core.event.NotificationService");
		
		// definir les agents à lancer
		var agent = new StringBuilder();
		
		while (!createdAgent) {
			if (f.veutCreerAgent) {
				agent = f.createAgent();
				f.veutCreerAgent = false;
				createdAgent = true;
			}
			System.out.println("");
		}
	
		prop.setProperty(Profile.AGENTS, agent.toString());
		
		// definir un profil de base
		var pMain = new ProfileImpl(prop);
				
		Runtime.instance().createMainContainer(pMain);
		
		while (true) {
			boolean demandeDenvoi = f.veutEnvoyer;
			
			if (demandeDenvoi) {
				System.out.println("J'ai reçu la demande");
				f.newMsg(f.enterChat.getText(),f.name);
				//agent.
				f.veutEnvoyer = false;
			}
			System.out.println("");
		}
	}
}
