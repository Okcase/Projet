package pandemus;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.core.Runtime;

public class TopicAgent {

	public static void main(String[] args) {
		// Pour les agents à lancer par JADE
		Properties prop = new ExtendedProperties();
		
		// Activer ou non l'interface JADE
		prop.setProperty(Profile.GUI, "false");
		
		// Activer le 'TopicManagementService'
		prop.setProperty(Profile.SERVICES, "jade.core.messaging.TopicManagementService; jade.core.event.NotificationService");
		
		// Définit les agents à lancer
		var agent = new StringBuilder();
		agent.append("Tommy:pandemus.agents.NewAgent;");
		//agent.append("John:pandemus.agents.NewAgent;");
		prop.setProperty(Profile.AGENTS, agent.toString());
		
		// Définit un profil de base
		var pMain = new ProfileImpl(prop);
		Runtime.instance().createMainContainer(pMain);
	}
}
