package pandemus.agents;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;


public class TestAgent extends Agent {
	public Queue<String> queue = new LinkedList<String>();
    
    public void stackMessage (String message) {
        queue.offer(message);
    }
    
    public void receiveMessage () {
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
    
    public void sendMessage(String msgToSend) {
    	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
    	String message = msgToSend;
		stackMessage(message);
		msg.setContent(message);
		if (!message.isEmpty()) {}
			//myAgent.send(msg);
    	
    }
	
	protected void setup() {
		
		Scanner scan = new Scanner(System.in);
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		try {
			// S'enregistre sur le topic créé
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			final AID topic = topicHelper.createTopic("General");
			topicHelper.register(this.getAID(), topic);
			
			Queue<String> queue = new LinkedList<String>();
			msg.addReceiver(topic);
			
			// Ajoute un Behaviour pour envoyer les messages sur le topic
			addBehaviour(new CyclicBehaviour(this) {
				public void action() {
					String message = scan.nextLine();
					stackMessage(message);
					msg.setContent(message);
					if (!message.isEmpty())
						myAgent.send(msg);
				}
			} );	

			// Ajoute un Behaviour pour recevoir les messages du topic
			addBehaviour(new CyclicBehaviour(this) {
				public void action() {
					receiveMessage();
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
