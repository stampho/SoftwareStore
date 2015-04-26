package hu.sed.prf.softwarestore.controller.core;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

@ViewScoped
@Named
public class Tab implements Serializable {

	private static final long serialVersionUID = 6557305584879749505L;

	public void changed(TabChangeEvent event) {
		TabView tabView = (TabView)event.getComponent();
		int tabIndex = tabView.getChildren().indexOf(event.getTab());
		tabView.setActiveIndex(tabIndex);
	}

}
