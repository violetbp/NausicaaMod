package mooklabs.nausicaamod;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import mooklabs.mookcore.Unused;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.WorldType;

/**
 * counts number of vowels and consonents in the first gui program ive ever created!!! (i like this alot)
 * 
 * @author victor
 * @date 3-10-13
 */
public class NausicaaDebugWindow extends JPanel {

	/**
	 * checks to see what button was press and does approprate action
	 * 
	 * @author mooklabs
	 */
	private static class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			// if clik exit end program
			if ("end".equals(command)) {
				Minecraft.getMinecraft().shutdown();
				Main.debugWrite("Shutting down....");
			} else if ("abort".equals(command)) {
				Main.debugWrite("ABORTING: I REALLY HOPE YOU MENT TO PRESS THIS");
				System.exit(0);
			} else if (command.equals("survival")) {
				Main.debugWrite("GameMode set to survival");
				nSetGameMode(0);
			} else if (command.equals("creative")) {
				Main.debugWrite("GameMode set to creative");
				nSetGameMode(1);
			} else if (command.equals("day")) {
				for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
					MinecraftServer.getServer().worldServers[j].setWorldTime(0);
				Main.debugWrite("Time set to day.");
			} else if (command.equals("night")) {
				for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
					MinecraftServer.getServer().worldServers[j].setWorldTime(13000);
				Main.debugWrite("Time set to night.");

			} else if (command.equals("heal")) {
				Minecraft.getMinecraft().thePlayer.heal(30);

			} else if (command.equals("newWorld")) {
				Minecraft.getMinecraft().launchIntegratedServer("TESTING", "TESTING", new WorldSettings(12341234, GameType.CREATIVE, true, false, WorldType.FLAT));

			}

		}

	}

	/**
	 * Changes all players gamemodes <br>
	 * HOLY SH*T THIS TOOK A LONG TIME AND IT SETS EVERYONES GAME TYPE *crys*
	 * 
	 * @param modeType
	 */
	static void nSetGameMode(int modeType) {
		try {
			Minecraft.getMinecraft().thePlayer.setGameType(modeType == 0 ? GameType.SURVIVAL : GameType.CREATIVE);
			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
				for (int i = 0; i < MinecraftServer.getServer().worldServers[i].playerEntities.size(); ++i)
					((EntityPlayerMP) (MinecraftServer.getServer().worldServers[j].playerEntities.get(i))).setGameType(modeType == 0 ? GameType.SURVIVAL : GameType.CREATIVE);
		} catch (Exception e) {
			System.err.println("Error occured when trying to change gamemode");
			Main.debugWrite("Error occured when trying to change gamemode");
			System.err.println(e.getLocalizedMessage());
		}
	}

	// Textbox output
	static JTextPane textOutput = new JTextPane();

	static String prevText = "";
	static byte prevMessagesTheSame = 0;

	/**
	 * writes text, will only write 3 lines of the same text
	 * 
	 * @param string to display
	 * @return wheatehr it displayed
	 */
	public static boolean write(String str) {
		if (prevText.equals(str)) prevMessagesTheSame++;
		else prevMessagesTheSame = 0;

		prevText = str;

		if (prevMessagesTheSame < 4) {// only 3 messages
			textOutput.setText(textOutput.getText() + "\n" + str);
			return true;
		}
		return false;

	}

	/**
	 * DONOT USE
	 * 
	 * @param str
	 */
	@Unused
	public static void overWrite(String str) {
		textOutput.setText(str);
	}

	// make the JFrame global
	static JFrame window;

	public static void init() {
		// was going to use these but didnt work well, still change the width and hieht
		int width = 400;
		int height = 300;
		// inits the listener
		ButtonHandler listener = new ButtonHandler();

		// inits buttons
		JButton end = new JButton("Shutdown Minecraft");
		JButton abort = new JButton("ABORT Minecraft");
		/* JButton setTimeDay = new JButton("Set time to day");
		 * JButton setTimeNight = new JButton("Set time to night");
		 * JButton setToCreative = new JButton("Set to creative mode");
		 * JButton setToSurvival = new JButton("Set to survival mode");
		 * JButton heal = new JButton("Heal Player"); */
		JButton newWorld = new JButton("Test World");

		// button listeners
		end.addActionListener(listener);
		abort.addActionListener(listener);
		/* setTimeDay.addActionListener(listener);
		 * setTimeNight.addActionListener(listener);
		 * setToCreative.addActionListener(listener);
		 * setToSurvival.addActionListener(listener);
		 * heal.addActionListener(listener); */
		newWorld.addActionListener(listener);

		// action commands --- string passed to action listener to deside what to do
		end.setActionCommand("end");
		end.setToolTipText("This really does exit minecraft. No joke.");

		abort.setActionCommand("abort");
		abort.setToolTipText("This kills minecraft. With a knife. No wait. A kill signal. My bad.\n But really dont push this unless minecraft stops responding completely.\nIts just easyer than opening terminal");
		newWorld.setActionCommand("newWorld");

		/*setTimeDay.setActionCommand("day");
		 * setTimeNight.setActionCommand("night");
		 * setToCreative.setActionCommand("creative");
		 * setToSurvival.setActionCommand("survival");
		 * heal.setActionCommand("heal"); */
		newWorld.setActionCommand("newWorld");

		// panels to store stuff
		JPanel content = new JPanel();
		JPanel options = new JPanel();
		JPanel output = new JPanel();

		// idk
		content.setLayout(new BorderLayout());

		// adds in teh other content boxes, i probably didnt need to do this
		content.add(output, BorderLayout.NORTH);
		content.add(options, BorderLayout.SOUTH);

		// add output
		output.add(textOutput, BorderLayout.CENTER);

		// add in the 4 buttons to the button bar then...
		JPanel buttonBar = new JPanel();
		buttonBar.setLayout(new GridLayout(3, 2, 5, 5));//  grid of buttons, 5px gap inbetween

		// ...add buttons then....
		/* buttonBar.add(setToCreative);
		 * buttonBar.add(setToSurvival);
		 * buttonBar.add(setTimeDay);
		 * buttonBar.add(setTimeNight);
		 * buttonBar.add(heal); */
		buttonBar.add(end);
		buttonBar.add(abort);
		buttonBar.add(newWorld);

		// ....add the button bar
		options.add(buttonBar, BorderLayout.SOUTH);

		// creates the gui, kinda really importaint
		window = new JFrame("NausicaaMod debug/console output window");
		window.setContentPane(content);
		window.setSize(width, height);
		window.setLocation(400, 400);
		window.setVisible(true);// make it visible

		// /stuff i just added
		textOutput.setAutoscrolls(true);
		textOutput.setText("*Debug Start* NausicaaMod version: " + Main.VERSION+"Data: \n"+
		System.getProperty("os.name")+ ",  "+
		System.getProperty("os.version")+ ",  "+
					System.getProperty("os.arch")+ ",  "+
					System.getProperty("java.version")+ ",  "+
					System.getProperty("java.vendor")+ ",  "+
					System.getProperty("sun.arch.data.model"));
	}
}