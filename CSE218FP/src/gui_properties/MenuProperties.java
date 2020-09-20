package gui_properties;

import javafx.scene.control.Menu;

public class MenuProperties extends Menu{
	
	public MenuProperties(String name) {
		this.setText(name);
	}

	public void addMenuItems(MenuProperties menu, MenuItemProperties...menuItems) {
		for(MenuItemProperties menuItem : menuItems) {
			menu.getItems().add(menuItem);
		}
	}
	
}
