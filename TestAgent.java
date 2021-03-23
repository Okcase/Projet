package pandemus.agents;

import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class TestAgent extends Agent {
	
	protected void setup() {
		
		Scanner scan = new Scanner(System.in);
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		try {
			// Register to messages about topic "JADE"
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			final AID topic = topicHelper.createTopic("General");
			topicHelper.register(this.getAID(), topic);
			
			msg.addReceiver(topic);
			
			addBehaviour(new CyclicBehaviour() {
				public void action() {
					String message = scan.nextLine();
					msg.setContent(message);
					if (!message.isEmpty())
						myAgent.send(msg);
				}
			} );	

			// Add a behaviour collecting messages about topic "JADE"
			addBehaviour(new CyclicBehaviour(this) {
				public void action() {
					ACLMessage msg = myAgent.receive(MessageTemplate.MatchTopic(topic));
					if (msg != null) {
						System.out.println(msg.getContent());
						msg = null;
					}
				} 
			});
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected void takeDown() {
		try {
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			AID topic = topicHelper.createTopic("General");
			topicHelper.deregister(this.getAID(), topic);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
