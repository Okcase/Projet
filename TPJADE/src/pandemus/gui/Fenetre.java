package pandemus.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener{
	JPanel panel = new JPanel();
	Color bg = new Color(10, 10, 10);
	Color txt = new Color(245, 245, 245);
	Color txtchat = new Color(245, 245, 120);
	Color txtarea = new Color(20, 20, 20);
	Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	int screenX = (int) (tailleEcran.getWidth() - tailleEcran.getWidth()/4);
	int screenY = (int) (tailleEcran.getHeight() - tailleEcran.getHeight()/4);
	String name = "John Smith";
	public boolean veutCreerAgent = false;
	/*
	 *  Pages de la fenêtre
	 */
	boolean _title = true;
	boolean _chat = false;
	
	/*
	 *  Boutons
	 */
	JButton createButton = new JButton("Jouer");
	JButton nameButton = new JButton("Renommer");
	JButton backButton = new JButton("Retour");
	JButton sendButton = new JButton("Envoyer");
	JButton quitButton = new JButton("Quitter");
	
	/*
	 * Zone de texte
	 */
	JTextArea enterName = new JTextArea("");
	JTextArea enterChat = new JTextArea("");
	
	/*
	 * Chat
	 */
	
	String[] chat = {"","","","","","","","","",""};
	
	public Fenetre() {
	/*
	 *  Initialisation de la fenêtre
	 */
		this.setTitle("Pandemus");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backButton.addActionListener(this);
		createButton.addActionListener(this);
		nameButton.addActionListener(this);
		sendButton.addActionListener(this);
		quitButton.addActionListener(this);
		
		this.setSize(screenX, screenY);
		this.setLocation((int)(tailleEcran.getWidth() / 2) - screenX/2,(int) (tailleEcran.getHeight() / 2) - screenY/2);
		this.updateFenetre();
		
		
	}
	
	public StringBuilder createAgent () {
		var lesAgents = new StringBuilder();
		lesAgents.append(name + ":pandemus.agents.TestAgent;");
		lesAgents.append("tommy:pandemus.agents.TestAgent;");
		lesAgents.append("shelby:pandemus.agents.TestAgent;");
		return lesAgents;
	}
	
	public void initMsg() {
		for (int i = 0; i < chat.length; i++) {
				chat[i] = "";
		}
	}
	
	public void newMsg(String msg,String user) {
		for (int i = (chat.length - 2); i >= 0; i--) {
			chat[i + 1] = chat[i];
		}
		chat[0] = "<"+user+"> "+msg;
		System.out.println("Got new message from " +user+ ":" +msg);
	}
		
	public void updateFenetre() {
		if (_title) {
			panel.setBackground(bg);
			JLabel title = new JLabel("Pandemus",JLabel.CENTER);
			JLabel nickname = new JLabel("Nom utilisé: " + name,JLabel.CENTER);
			
			nameButton.setBounds(screenX - (screenX/5),20,screenX/10,50);
			quitButton.setBounds(screenX - (screenX/5),20,screenX/10,50);
			createButton.setBounds(screenX/2 - (int)(screenX*0.15), (int)(screenY*0.7), (int)(screenX*0.3), (int)(screenY*0.15));
			
			title.setFont(new Font("verdana",1,40));
			title.setForeground(txt);
			title.setBounds(0, 20, screenX, 50);
			
			nickname.setFont(new Font("verdana",1,20));
			nickname.setForeground(txt);
			nickname.setBounds(0, screenY/3, screenX, 50);
			
			enterName.setForeground(txtarea);
			enterName.setBounds(screenX/2 - screenX/5,(screenY/2)+(screenY/10),screenX/5 * 2, screenY/30);
			
			nameButton.setBounds(screenX/2 + (screenX/5) + 10,(screenY/2)+(screenY/10),screenX/10, screenY/30);
			
			panel.setLayout(null);
			panel.add(title);
			panel.add(nickname);
			panel.add(createButton);
			panel.add(enterName);
			panel.add(nameButton);
			panel.add(quitButton);
		}  else if (_chat) {
			panel.setBackground(bg);
			JLabel title = new JLabel("Pandemus",JLabel.CENTER);
			enterChat = new JTextArea("",100,5);
			backButton.setBounds(screenX - (screenX/5),20,screenX/10,50);
			
			title.setFont(new Font("verdana",1,40));
			title.setForeground(txt);
			title.setBounds(0, 20, screenX, 50);
			
			enterChat.setForeground(txtarea);
			enterChat.setBounds(screenX/2 - screenX/5,(screenY)-(screenY/10),screenX/5 * 2, screenY/30);
			
			sendButton.setBounds(screenX/2 + (screenX/5) + 10,(screenY)-(screenY/10),screenX/10, screenY/30);
			
			JLabel chat9 = new JLabel(chat[9],JLabel.LEFT);
			JLabel chat8 = new JLabel(chat[8],JLabel.LEFT);
			JLabel chat7 = new JLabel(chat[7],JLabel.LEFT);
			JLabel chat6 = new JLabel(chat[6],JLabel.LEFT);
			JLabel chat5 = new JLabel(chat[5],JLabel.LEFT);
			JLabel chat4 = new JLabel(chat[4],JLabel.LEFT);
			JLabel chat3 = new JLabel(chat[3],JLabel.LEFT);
			JLabel chat2 = new JLabel(chat[2],JLabel.LEFT);
			JLabel chat1 = new JLabel(chat[1],JLabel.LEFT);
			JLabel chat0 = new JLabel(chat[0],JLabel.LEFT);
			
			chat0.setFont(new Font("verdana",1,15));chat0.setForeground(txtchat);chat0.setBounds(screenX/2 - screenX/5, 280, screenX, 20);
			chat1.setFont(new Font("verdana",1,15));chat1.setForeground(txtchat);chat1.setBounds(screenX/2 - screenX/5, 260, screenX, 20);
			chat2.setFont(new Font("verdana",1,15));chat2.setForeground(txtchat);chat2.setBounds(screenX/2 - screenX/5, 240, screenX, 20);
			chat3.setFont(new Font("verdana",1,15));chat3.setForeground(txtchat);chat3.setBounds(screenX/2 - screenX/5, 220, screenX, 20);
			chat4.setFont(new Font("verdana",1,15));chat4.setForeground(txtchat);chat4.setBounds(screenX/2 - screenX/5, 200, screenX, 20);
			chat5.setFont(new Font("verdana",1,15));chat5.setForeground(txtchat);chat5.setBounds(screenX/2 - screenX/5, 180, screenX, 20);
			chat6.setFont(new Font("verdana",1,15));chat6.setForeground(txtchat);chat6.setBounds(screenX/2 - screenX/5, 160, screenX, 20);
			chat7.setFont(new Font("verdana",1,15));chat7.setForeground(txtchat);chat7.setBounds(screenX/2 - screenX/5, 140, screenX, 20);
			chat8.setFont(new Font("verdana",1,15));chat8.setForeground(txtchat);chat8.setBounds(screenX/2 - screenX/5, 120, screenX, 20);
			chat9.setFont(new Font("verdana",1,15));chat9.setForeground(txtchat);chat9.setBounds(screenX/2 - screenX/5, 100, screenX, 20);
			
			
			panel.setLayout(null);
			panel.add(title);
			panel.add(enterChat);
			panel.add(backButton);
			panel.add(sendButton);
			panel.add(chat0);panel.add(chat1);panel.add(chat2);panel.add(chat3);panel.add(chat4);
			panel.add(chat5);panel.add(chat6);panel.add(chat7);panel.add(chat8);panel.add(chat9);
		}
		
		
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == createButton) {
			_title = false;
			_chat = true;
			initMsg();
			veutCreerAgent = true;
		}
		
		if (source == nameButton) {
			String newName = enterName.getText();
				if (newName.length() > 17) {
					newName = newName.substring(0,17);
				}
				name = newName;
		}
		
		if (source == backButton) {
			_chat = false;
			_title = true;
		}
		
		if (source == sendButton) {
			if (!(enterChat.getText() == null) && !(enterChat.getText().isEmpty()))
				newMsg(enterChat.getText(),name);
		}
		
		if (source == quitButton) {
			System.exit(0);;
		}
		
		panel.removeAll();
		updateFenetre();
	}
	
}
