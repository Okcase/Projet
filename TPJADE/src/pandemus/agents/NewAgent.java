package pandemus.agents;

import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pandemus.gui.Fenetre;

public class NewAgent extends Agent{
	
	protected void setup() {
		
		Scanner scan = new Scanner(System.in);
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		Fenetre f = new Fenetre();
		
		
		try {
			// Création du topic "Général"
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			final AID topic = topicHelper.createTopic("General");
			
			// Définit le topic comme destinataire
			msg.addReceiver(topic);
			
			// Comportement cyclique pou envoyer un message
			addBehaviour(new CyclicBehaviour() {
				String message;
				public void action() {
					message = scan.nextLine();
					msg.setContent(message);
					f.newMsg(message, getLocalName());
					if (!message.isEmpty()) {
						myAgent.send(msg);
					}
				}
			} );		
			
			
			// S'enregistre dans le topic "Général"
			topicHelper.register(this.getAID(), topic);
			
			// Comportement cyclique pour recevoir les messages du topic
			addBehaviour(new CyclicBehaviour(this) {
				public void action() {
					ACLMessage msg = myAgent.receive(MessageTemplate.MatchTopic(topic));
					if (msg != null)
						println(msg.getContent());
					else 
						block();
				}
			} );
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void println(String message) {
		System.out.println(getLocalName() + " : " + message);
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
