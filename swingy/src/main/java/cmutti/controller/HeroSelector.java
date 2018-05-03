package cmutti.controller;

import cmutti.model.heroes.*;
import cmutti.model.heroes.AHero;
import cmutti.model.heroes.AHero;
import cmutti.view.cli.FrameCLI;
import cmutti.view.gui.FrameGUI;
import java.util.ArrayList;

public class HeroSelector {
	static ArrayList<Class<? extends AHero>> heroTypes;
	static {
		heroTypes = new ArrayList<Class<? extends AHero>>();
		heroTypes.add(Backpacker.class);
		heroTypes.add(Healer.class);
		heroTypes.add(KarateGirl.class);
		heroTypes.add(KarateMan.class);
	}

	Swingy swingy = Swingy.getInstance();
	FrameGUI guiFrame = null;
	FrameCLI cliFrame = null;
	AHero hero = null;
	ArrayList<AHero> savedHeroes;
	int activeIdx = 0;
	boolean creatingNew = true;
	String[] heroTypesLegend = null;
	String[] savedHeroesLegend = null;

	HeroSelector(FrameGUI guiFrame, FrameCLI cliFrame) {
		this.guiFrame = guiFrame;
		this.cliFrame = cliFrame;

		// TODO retrieve saved heroes on startup
		savedHeroes = new ArrayList<AHero>();
	}

	public void start() {
		activeIdx = 0;
		creatingNew = true;
		try {
			hero = heroTypes.get(activeIdx).getConstructor(String.class, int.class).newInstance(heroTypes.get(activeIdx).getSimpleName(), 1);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		heroTypesLegend = new String[heroTypes.size()];
		for (int i = 0; i < heroTypesLegend.length; i++) {
			heroTypesLegend[i] = heroTypes.get(i).getSimpleName();
		}

		savedHeroesLegend = new String[savedHeroes.size()];
		for (int i = 0; i < savedHeroesLegend.length; i++) {
			savedHeroesLegend[i] = savedHeroes.get(i).getClass().getSimpleName();
		}

		if (guiFrame != null)
			guiFrame.startSelectionPanel();
		// if (cliFrame != null)
		// 	cliFrame.startSelectionPanel(savedHeroes);

		// Trick to be sure to enable creatingNew
		creatingNew = false;
		toggleMode();
	}

	public void changedSelectedItem(int newIdx) {
		int tmpIndex = activeIdx;
		activeIdx = newIdx;
		String name = (creatingNew) ? heroTypesLegend[activeIdx] : "";
		if (!createHero(name)) {
			// An error occurred, revert
			activeIdx = tmpIndex;
			return;
		}

		if (creatingNew) {
			if (guiFrame != null)
				guiFrame.selectionPanel.updateHeroPanel(hero);

		}
		else {

		}
	}

	public void toggleMode() {
		// Exit if trying to load saved heroes and dont have any
		if (creatingNew && savedHeroes.size() == 0)
			return;

		// Common operations
		creatingNew = !creatingNew;
		int tmpIndex = activeIdx;
		activeIdx = 0;
		String name = (creatingNew) ? heroTypesLegend[activeIdx] : "";
		if (!createHero(name)) {
			// An error occurred, revert
			creatingNew = !creatingNew;
			activeIdx = tmpIndex;
			return;
		}

		// Specific changes
		String[] comboLabels = (creatingNew) ? heroTypesLegend : savedHeroesLegend;

		if (guiFrame != null)
			guiFrame.selectionPanel.updateMode(comboLabels, creatingNew, hero);
	}

	private boolean createHero(String name) {
		if (creatingNew) {
			try {
				hero = heroTypes.get(activeIdx).getConstructor(String.class, int.class).newInstance(name, 1);
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		else {
			hero = savedHeroes.get(activeIdx);
		}

		return true;
	}

	public void confirmSelection(String name) {
		createHero(name);

		// hero = new KarateMan("yo2", 1);
		// hero = new Backpacker("yo2", 1);
		// hero = new KarateGirl("yo2", 10);
		// hero = new Healer("yo2", 1);
		swingy.startMainGame(hero);
	}
}
