package cmutti.view;

import cmutti.model.heroes.AHero;

public interface ISelectionPanel {
	public void updateSelectionMode(String[] comboLabels, boolean creatingNew, AHero hero);
	public void updateHeroSelected(AHero hero);
}
