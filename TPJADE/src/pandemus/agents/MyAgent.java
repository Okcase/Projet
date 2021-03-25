package pandemus.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class MyAgent extends Agent {

	@Override
	protected void setup() {
		
		try {
			// Récupération du canal général
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			AID general = topicHelper.createTopic("General");
			
			//demande la reception des messages du chat general
			topicHelper.register(this.getAID(), general);

			
			var message = new ACLMessage(ACLMessage.INFORM);
			message.addReceiver(general);
			message.setContent("Le Hello World du désespoir");
			this.send(message);

			var messageRecu = this.receive(MessageTemplate.MatchTopic(general));
			while (messageRecu != null) {
				System.out.println("message récupéré : " + messageRecu.getContent());
				messageRecu = this.receive(MessageTemplate.MatchTopic(general));
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*@Override
	protected void afterMove() {
		// Récupération du canal général
		var topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
		AID general = topicHelper.createTopic("General");
		
		//demande la reception des messages du chat general
		topicHelper.register(this.getAID(), general);
	}

	
	@Override
	protected void takeDown() {
		System.out.println("L'agent " + this.getAID().getLocalName() + "est mort");
	}*/
}
