package pandemus.agents;

import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;


public class TopicMessageSenderAgent extends Agent {
	
	protected void setup() {
		try {
			Scanner scan = new Scanner(System.in);
			
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			final AID topic = topicHelper.createTopic("General");
			
			msg.addReceiver(topic);
			
			addBehaviour(new CyclicBehaviour() {
				public void action() {
					String message = scan.nextLine();
					msg.setContent(message);
					if (!message.isEmpty())
						myAgent.send(msg);
					
				}
			} );
		}
		catch (Exception e) {
			System.err.println("Agent "+getLocalName()+": ERROR creating topic \"General\"");
			e.printStackTrace();
		}
	}
}
