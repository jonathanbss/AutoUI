package code.templates.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

import code.interfaces.Entity;

public class GridPage<T extends Entity> extends VerticalLayout {
	
	private static final long serialVersionUID = -4912519062108057979L;
	private Grid<T> grid;
	private HorizontalLayout buttonLayout;
	
	//TODO; Implement default buttons for add and delete
	public GridPage(T entity, DataProvider<T, Void> dataProvider) {
		grid = new Grid<T>();
		grid.setColumns(entity.getGridColumns());
		grid.setDataProvider(dataProvider);
		
		add(grid, buttonLayout);
	}
	
	public Grid<T> getGrid() {
		return grid;
	}
	
	public HorizontalLayout getButtonLayout() {
		return buttonLayout;
	}
}
