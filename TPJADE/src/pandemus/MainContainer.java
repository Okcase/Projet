package pandemus;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {

	public static void main(String[] args) {
		try {
			Runtime runtime = Runtime.instance();
			Properties proprietes = new ExtendedProperties();
			proprietes.setProperty(Profile.GUI, "true");
			Profile profil = new ProfileImpl(proprietes);
			AgentContainer mainContainer = runtime.createMainContainer(profil);
			mainContainer.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
