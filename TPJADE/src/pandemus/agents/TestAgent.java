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
		try {
			Scanner scan = new Scanner(System.in);
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			final AID topic = topicHelper.createTopic("General");
			
			msg.addReceiver(topic);
			
			addBehaviour(new CyclicBehaviour() {
				public void action() {
					String message = (getLocalName() + "vient d'arriver !");
					message = scan.nextLine();
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
		
		try {
			// Register to messages about topic "JADE"
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			final AID topic = topicHelper.createTopic("General");
			topicHelper.register(this.getAID(), topic);
			
			// Add a behaviour collecting messages about topic "JADE"
			addBehaviour(new CyclicBehaviour(this) {
				public void action() {
					ACLMessage msg = myAgent.receive(MessageTemplate.MatchTopic(topic));
					while (true){
						if (msg != null) {
							System.out.println(msg.getContent());
							msg = null;
						}
						else 
							break;
					}
				}
			} );
		}
		catch (Exception e) {
			System.err.println("Agent "+getLocalName()+": ERROR registering to topic \"General\"");
			e.printStackTrace();
		}
	}
	
	private void println(String message) {
		System.out.println("Agent" + getLocalName() + " : " + message);
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
