package pandemus;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;


public class MonContainer {

	public static void main(String[] args) {
		try {
			Runtime runtime = Runtime.instance();
			Profile profil = new ProfileImpl(false);
			profil.setParameter(Profile.MAIN_HOST, "localhost");
			AgentContainer agentContainer = runtime.createAgentContainer(profil);
			AgentController agentController = agentContainer.createNewAgent("Sender", "pandemus.agents.TopicMessageSenderAgent", null);
			AgentController agentController2 = agentContainer.createNewAgent("Receiver", "pandemus.agents.TopicMessageReceiverAgent", null);
			agentController.start();
			agentController2.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
