package code.processor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import code.annotations.UIElement;

public class UIProcessor {
	
	public List<Field> getFields(Class<?> entityClass) {
		List<Field> annotatedFields = new ArrayList<>();
		
		Field[] allFields = entityClass.getDeclaredFields();
		for(Field f : allFields) {
			if(f.isAnnotationPresent(UIElement.class)) {
				annotatedFields.add(f);
			}
		}
		annotatedFields.sort(new Comparator<Field>() {

			@Override
			public int compare(Field o1, Field o2) {
				UIElement first = o1.getAnnotation(UIElement.class);
				UIElement second = o2.getAnnotation(UIElement.class);
				return Integer.compare(first.order(), second.order());
			}
		});
		return annotatedFields;
	}
	
	public Component getComponentByField(Field f) {
		if(!f.isAnnotationPresent(UIElement.class)) {
			throw new RuntimeException("UIElement annotation not present for field " + f.getName());
		}
		
		UIElement uiElement = f.getAnnotation(UIElement.class);
		Component component = null;
		if(uiElement.overrideComponent() != null) {
			Constructor<?>[] test = uiElement.overrideComponent().getDeclaredConstructors();
			for(Constructor<?> c : test) {
				if(c.getParameterCount() == 0) {
					c.setAccessible(true);
					try {
						component = (Component) c.newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		if(component == null) {
			if(f.getType() == Double.class
					|| f.getType() == Float.class
					|| f.getType() == BigDecimal.class
					|| f.getType() == Integer.class) {
				component = new NumberField();
			} else if(f.getType() == String.class) {
				component = new TextField();
			} else if(f.getType() == Date.class 
					|| f.getType() == LocalDate.class) {
				component = new DatePicker();
			} else if(f.getType() == Boolean.class) {
				component = new Checkbox();
			} else if(f.isEnumConstant()) {
				component = new Select<>();
			} else {
				throw new RuntimeException("Type cannot be converted to component.");
			}
		}
		
		return component;
	}
}
