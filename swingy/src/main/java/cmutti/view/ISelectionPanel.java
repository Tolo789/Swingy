package cmutti.view;

import cmutti.model.heroes.AHero;

public interface ISelectionPanel {
	public void updateSelectionMode(String[] comboLabels, boolean creatingNew, AHero hero, boolean canToggle);
	public void updateHeroSelected(int heroIdx, AHero hero);
}
