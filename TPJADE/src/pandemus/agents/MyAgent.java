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
			// R�cup�ration du canal g�n�ral
			TopicManagementHelper topicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			AID general = topicHelper.createTopic("General");
			
			//demande la reception des messages du chat general
			topicHelper.register(this.getAID(), general);

			
			var message = new ACLMessage(ACLMessage.INFORM);
			message.addReceiver(general);
			message.setContent("Le Hello World du d�sespoir");
			this.send(message);

			var messageRecu = this.receive(MessageTemplate.MatchTopic(general));
			while (messageRecu != null) {
				System.out.println("message r�cup�r� : " + messageRecu.getContent());
				messageRecu = this.receive(MessageTemplate.MatchTopic(general));
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*@Override
	protected void afterMove() {
		// R�cup�ration du canal g�n�ral
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
