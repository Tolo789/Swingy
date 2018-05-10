package cmutti.controller;

import cmutti.model.heroes.*;
import cmutti.model.heroes.AHero;
import cmutti.view.cli.FrameCLI;
import cmutti.view.gui.FrameGUI;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

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

		savedHeroes = DatabaseController.retrieveHeroes(cliFrame != null);
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
			savedHeroesLegend[i] = savedHeroes.get(i).getName();
		}
		// Avoid duplicate name errors
		int sameNameCount;
		for (int i = 0; i < savedHeroesLegend.length; i++) {
			sameNameCount = 1;
			for (int j = 0; j < i; j++) {
				if (savedHeroesLegend[i].equals(savedHeroesLegend[j]))
					sameNameCount++;
			}
			savedHeroesLegend[i] += (sameNameCount > 1) ? " (" + sameNameCount + ")" : "";
		}

		if (guiFrame != null)
			guiFrame.startSelectionPanel();
		if (cliFrame != null)
			cliFrame.startSelectionPanel();

		// Trick: if at least one hero saved then show load choice first, createNew otherwise
		creatingNew = (savedHeroesLegend.length > 0) ? true : false;
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

		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.selectionPanel.updateHeroSelected(activeIdx, hero);
				}
			});
		}
		if (cliFrame != null)
			cliFrame.choicePanel.updateHeroSelected(activeIdx, hero);
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
		final String[] comboLabels = (creatingNew) ? heroTypesLegend : savedHeroesLegend;
		final boolean isCreatingNew = creatingNew;
		final AHero selectedHero = hero;
		final boolean canToggle = (creatingNew && savedHeroes.size() == 0) ? false : true;

		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.selectionPanel.updateSelectionMode(comboLabels, isCreatingNew, selectedHero, canToggle);
				}
			});
		}
		if (cliFrame != null) {
			cliFrame.choicePanel.updateSelectionMode(comboLabels, creatingNew, hero, canToggle);
		}
	}

	private boolean createHero(String name) {
		if (creatingNew) {
			// TODO: annotation validation
			if (name == null || name.equals(""))
				return false;
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

		// TODO: annotation validation
		// At least one char, only alphanum and spaces, no trailing spaces, max 20 chars

		return true;
	}

	public void confirmSelection(String name) {
		if (!createHero(name))
			return;

		// hero = new KarateMan("yo2", 1);
		// hero = new Backpacker("yo2", 1);
		// hero = new KarateGirl("yo2", 10);
		// hero = new Healer("yo2", 1);
		if (cliFrame != null)
			System.out.println("\nStarting game with following Hero:");
		swingy.startMainGame(hero);
	}
}
