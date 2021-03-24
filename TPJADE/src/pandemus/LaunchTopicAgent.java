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
		
		//services et agents à lancer par JADE
		Properties prop = new ExtendedProperties();
		
		// autoriser la fenêtre de contrôle
		prop.setProperty(Profile.GUI, "false");
		
		// activer le 'TopicManagementService'
		prop.setProperty(Profile.SERVICES, "jade.core.messaging.TopicManagementService; jade.core.event.NotificationService");
		
		// definir les agents à lancer
		var lesAgents = new StringBuilder();
		lesAgents.append("agent:pandemus.agents.TestAgent;");
		prop.setProperty(Profile.AGENTS, lesAgents.toString());
		
		// definir un profil de base
		var pMain = new ProfileImpl(prop);
		
		Runtime.instance().createMainContainer(pMain);
	}
}
