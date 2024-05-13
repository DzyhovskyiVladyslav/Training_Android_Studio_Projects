package com.example.mytrainingapp;

import androidx.lifecycle.AndroidViewModel;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2019/10/21
 */

public abstract class AbstractFormVM extends AndroidViewModel {

	private AbstractForm form;

	public static AbstractFormVM getInstance(AbstractForm form, Class clazz) {
		try {
			Constructor constructor = clazz.getConstructor(AbstractForm.class);
			return (AbstractFormVM) constructor.newInstance(form);
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	public AbstractFormVM(AbstractForm form) {
		super(form.getActivity().getApplication());
		this.form = form;
	}

	public AbstractForm getForm() {
		return form;
	}
}