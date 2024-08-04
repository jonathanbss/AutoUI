package code.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vaadin.flow.component.Component;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UIElement {
	Label label() default @Label();
	Binding binding();
	Class<? extends Component> overrideComponent();
	int order() default Integer.MAX_VALUE;
}
