package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShippingInformation {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty gno;
	private final SimpleStringProperty name;
	private final SimpleStringProperty place;
	private final SimpleStringProperty factory;
	private final SimpleStringProperty amount;
	private final MyCheckBox cBox;
	
	public ShippingInformation(Integer id, String gno, String name, String place, String factory, String amount) {
		// TODO Auto-generated constructor stub
		this.id = new SimpleIntegerProperty(id);
		this.gno = new SimpleStringProperty(gno);
		this.name = new SimpleStringProperty(name);
		this.place = new SimpleStringProperty(place);
		this.factory = new SimpleStringProperty(factory);
		this.amount = new SimpleStringProperty(amount);
		this.cBox = new MyCheckBox();
	}
	
	public MyCheckBox getMyCheckBox() {
		return cBox;
	}
	
	public String getAmount() {
		return amount.get();
	}
	
	public void setAmount(String amount) {
		this.amount.set(amount);
	}
	
	public Integer getId() {
		return id.get();
	}
	
	public void setId(Integer id) {
		this.id.set(id);
	}
	
	public String getFactory() {
		return factory.get();
	}
	
	public void setFactory(String factory) {
		this.factory.set(factory);
	}
	
	public String getGno() {
		return gno.get();
	}
	
	public void setGno(String gno) {
		this.gno.set(gno);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getPlace() {
		return place.get();
	}
	
	public void setPlace(String place) {
		this.place.set(place);
	}
}
