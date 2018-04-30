package cmutti.view.cli;

import cmutti.model.heroes.AHero;
import cmutti.view.IHeroPanel;

public class HeroPanelCLI implements IHeroPanel {
	protected AHero hero = null;

	HeroPanelCLI(AHero hero) {
		this.hero = hero;
		update();
	}

	public void update() {
		String str = hero.getName() + " Lv." + hero.getLevel() + "\n";

		str += "Xp: " + hero.getXp() + "/" + hero.getNeededXp() + "\n";
		str += "Hp: " + hero.getHp() + "/" + hero.getMaxHp() + "\n";
		str += "Attack: " + hero.getAttack() + "\n";
		str += "Defense: " + hero.getDefense() + "\n";
		str += "Agility: " + hero.getAgility() + "\n";
		str += "Coordinates: " + hero.getPosX() + "-" + hero.getPosY() + "\n";


		str += "Artifacts bonus: \n";
		if (hero.getHelm() != null) {
			str += "    " + hero.getHelm().getName() + ": " + hero.getHelm().getBonusPresentation() + "\n";
		}
		else
			str += "    No helm equipped\n";

		if (hero.getArmor() != null) {
			str += "    " + hero.getArmor().getName() + ": " + hero.getArmor().getBonusPresentation() + "\n";
		}
		else
			str += "    No armor equipped\n";

		if (hero.getWeapon() != null) {
			str += "    " + hero.getWeapon().getName() + ": " + hero.getWeapon().getBonusPresentation() + "\n";
		}
		else
			str += "    No weapon equipped\n";

		System.out.println(str);
	}
}
