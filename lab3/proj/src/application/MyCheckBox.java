package application;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class MyCheckBox {
	CheckBox cBox;
	public MyCheckBox() {
		// TODO Auto-generated constructor stub
		cBox = new CheckBox();
	}
    public ObservableValue<CheckBox> getCheckBox()
    {
        return new  ObservableValue<CheckBox>() {
            @Override
            public void addListener(ChangeListener<? super CheckBox> listener) {

            }

            @Override
            public void removeListener(ChangeListener<? super CheckBox> listener) {

            }

            @Override
            public CheckBox getValue() {
                return cBox;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        };
    }
    
    public Boolean isSelected()
    {
        return cBox.isSelected();
    }
}
