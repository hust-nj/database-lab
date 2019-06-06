package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import javax.swing.JOptionPane;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class PurchaseSceneController extends SetProperty{
	@FXML
	private ComboBox<String> goodsChooseComboBox;
	@FXML
	private TextField amountTextField;
	@FXML
	private Button addAmountButton;
	@FXML
	private Button ensurePurchaseButton;
	@FXML TableView<PurchaseInformation> purchaseTableView;
	@FXML TableColumn<PurchaseInformation, String> gnoTableColumn;
	@FXML TableColumn<PurchaseInformation, String> nameTableColumn;
	@FXML TableColumn<PurchaseInformation, String> placeTableColumn;
	@FXML TableColumn<PurchaseInformation, String> factoryTableColumn;
	@FXML TableColumn<PurchaseInformation, String> amountTableColumn;
	@FXML TableColumn<PurchaseInformation, Number> idTableColumn;
	@FXML TableColumn<PurchaseInformation, CheckBox> choiceTableColumn;
	@FXML Button clearPurchaseButton;
	@FXML Button deletePurchaseButton;
	
	@FXML ComboBox<String> inquirePnoChooseComboBox;
	@FXML ComboBox<String> inquireStateChooseComboBox;
	@FXML TableView<InquirePurchaseInformation> inquirePurchaseTableView;
	@FXML TableColumn<InquirePurchaseInformation, Number> inquireIdTableColumn;
	@FXML TableColumn<InquirePurchaseInformation, String> inquireGnoTableColumn;
	@FXML TableColumn<InquirePurchaseInformation, String> inquireNameTableColumn;
	@FXML TableColumn<InquirePurchaseInformation, String> inquirePlaceTableColumn;
	@FXML TableColumn<InquirePurchaseInformation, String> inquireFactoryTableColumn;
	@FXML TableColumn<InquirePurchaseInformation, Number> inquireAmountTableColumn;
	
	@FXML TableView<PurchaseInformation> modifyPurchaseTableView;
	@FXML TableColumn<PurchaseInformation, Number> modifyIdTableColumn;
	@FXML TableColumn<PurchaseInformation, String> modifyGnoTableColumn;
	@FXML TableColumn<PurchaseInformation, String> modifyNameTableColumn;
	@FXML TableColumn<PurchaseInformation, String> modifyPlaceTableColumn;
	@FXML TableColumn<PurchaseInformation, String> modifyFactoryTableColumn;
	@FXML TableColumn<PurchaseInformation, String> modifyAmountTableColumn;
	@FXML TableColumn<PurchaseInformation, CheckBox> modifyChoiceTableColumn;
	@FXML Button ensureModifyButton;
	@FXML Button cancelPurchaseButton;
	@FXML ComboBox<String> modifyPnoChooseComboBox;
	@FXML Button deleteModifyButton;
	
	private String inquirePno = "";
	private String modifyPno = "";
	
	
	public void myinitialize() {
		// TODO Auto-generated method stub
		idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		amountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		factoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("factory"));
		gnoTableColumn.setCellValueFactory(new PropertyValueFactory<>("gno"));
		nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		placeTableColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
		choiceTableColumn.setCellValueFactory(cellData -> cellData.getValue().getMyCheckBox().getCheckBox());
		
		inquireIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		inquireAmountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		inquireFactoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("factory"));
		inquireGnoTableColumn.setCellValueFactory(new PropertyValueFactory<>("gno"));
		inquireNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		inquirePlaceTableColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
		
		modifyIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		modifyAmountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		modifyFactoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("factory"));
		modifyGnoTableColumn.setCellValueFactory(new PropertyValueFactory<>("gno"));
		modifyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		modifyPlaceTableColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
		modifyChoiceTableColumn.setCellValueFactory(cellData -> cellData.getValue().getMyCheckBox().getCheckBox());
		
		modifyAmountTableColumn.setCellFactory(TextFieldTableCell.<PurchaseInformation>forTableColumn());
		modifyAmountTableColumn.setOnEditCommit(
			    (CellEditEvent<PurchaseInformation, String> t) -> {
			        ((PurchaseInformation) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setAmount(t.getNewValue());
			});	
		
		ObservableList<String> options = FXCollections.observableArrayList("待审核", "审核通过");
		inquireStateChooseComboBox.setItems(options);
		
		inquireStateChooseComboBox.setValue("待审核");
	}
	
	// Event Listener on Button[#addAmountButton].onAction
	@FXML
	public void OnClickAddAmountButton(ActionEvent event) {
		// TODO Autogenerated
		String name;
		Integer amount = 0;
		String stringamount;
		
		stringamount = amountTextField.getText();
		if (stringamount == null 
				|| stringamount.equals("")) {
			JOptionPane.showMessageDialog(null, "进货量输入内容为空，请重新输入！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		try {
			amount = Integer.parseInt(stringamount);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			amount = 0;
			JOptionPane.showMessageDialog(null, "进货量不为整数，请重新输入！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		name = goodsChooseComboBox.getValue();
		if (name == null 
				|| name.equals("")) {
			JOptionPane.showMessageDialog(null, "未选择货品名称，请选择产品！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		goodsChooseComboBox.setValue("");
		amountTextField.setText("");
		
		
		//修改TableView
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;
		
		String gno = "";
		String place = "";
		String factory = "";
		
		try {
			statement = connection.createStatement();
			sql = "SELECT goods.gno, goods.place, goods.factory FROM goods WHERE goods.`name` = '"
					+ name
					+ "'";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				gno = resultSet.getString(1);
				place = resultSet.getString(2);
				factory = resultSet.getString(3);
				break;
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<PurchaseInformation> data = FXCollections.observableArrayList();
		ObservableList<PurchaseInformation> newdata = FXCollections.observableArrayList();
		data = purchaseTableView.getItems();
		
		if (data == null) {
			String tempstring = amount.toString();
			newdata.add(new PurchaseInformation(1, gno, name, place, factory, tempstring));
			purchaseTableView.setItems(newdata);
			return;
		}
		
		boolean flag = false;
		Iterator<PurchaseInformation> iterator = data.iterator();
		while (iterator.hasNext()) {
			PurchaseInformation temp = iterator.next();
			if(temp.getName().equals(name))
			{
				flag = true;
				String tempstring = (amount+ Integer.parseInt(temp.getAmount())) + "";
				System.out.println(tempstring);
				temp.setAmount(tempstring);
				PurchaseInformation newtemp = new PurchaseInformation(temp.getId(), 
						temp.getGno(), temp.getName(), temp.getPlace(), 
						temp.getFactory(), tempstring);
				newdata.add(newtemp);
			}
			else {
				newdata.add(temp);
			}
			
		}

		if(!flag)
		{
			int id = data.size() + 1;
			String tempstring = amount.toString();
			newdata.add(new PurchaseInformation(id, gno, name, place, factory, tempstring));
		}
		purchaseTableView.setItems(newdata);
	}
	// Event Listener on Button[#ensurePurchaseButton].onAction
	@FXML
	public void OnClickEnsurePurchaseButton(ActionEvent event) {
		// TODO Autogenerated
		ObservableList<PurchaseInformation> data = purchaseTableView.getItems();
		
		if (data.size() == 0) {
			JOptionPane.showMessageDialog(null, "进货单为空！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;
		String pno = "000001";
		
		try {
			statement = connection.createStatement();
			sql = "SELECT MAX(purchase.pno) FROM purchase";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				pno = String.format("%06d", resultSet.getInt(1) + 1);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//插入进货单
		try {
			statement = connection.createStatement();
			sql = "INSERT INTO purchase VALUES('"
					+ pno
					+ "', 1, '"
					+ main.getEno()
					+ "', NULL);";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "插入进货单成功！", "success", JOptionPane.INFORMATION_MESSAGE);
		
		//插入进货_包含
		data = FXCollections.observableArrayList();
		data = purchaseTableView.getItems();
		PurchaseInformation temp = null;
		String gno = null;
		String amount = null;
		
		Iterator<PurchaseInformation> iterator = data.iterator();
		while (iterator.hasNext()) {
			temp = iterator.next();
			gno = temp.getGno();
			amount = temp.getAmount();
			
			try {
				statement = connection.createStatement();
				sql = "INSERT INTO purchase_include VALUES('"
						+ pno
						+ "', '"
						+ gno
						+ "', "
						+ amount
						+ ")";
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		purchaseTableView.setItems(null);
	}
	
	@Override
	void setMain(Main main) {
		// TODO Auto-generated method stub
		super.setMain(main);
		myinitialize();
	}
	
	@FXML 
	public void OnClickClearPurchaseButton(ActionEvent event) 
	{
		purchaseTableView.setItems(null);
	}
	
	@FXML public void OnClickDeletePurchaseButton(ActionEvent event) 
	{
		ObservableList<PurchaseInformation> data = purchaseTableView.getItems();
		ObservableList<PurchaseInformation> newdata = FXCollections.observableArrayList();
		
		int id = 1;
		String amount;
		String gno;
		String name;
		String place;
		String factory;
		
		Iterator<PurchaseInformation> iterator = data.iterator();
		while (iterator.hasNext()) {
			PurchaseInformation temp = iterator.next();
			if(!temp.getMyCheckBox().isSelected())
			{
				amount = temp.getAmount();
				gno = temp.getGno();
				name = temp.getName();
				place = temp.getPlace();
				factory = temp.getFactory();
				newdata.add(new PurchaseInformation(id, gno, name, place, factory, amount));
				id++;
			}
		}

		purchaseTableView.setItems(newdata);
	}
	
	@FXML public void OnClickGoodsChooseComboBox(MouseEvent event) 
	{
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<String> options = FXCollections.observableArrayList();
		String sql = null;
		
		try {
			statement = connection.createStatement();
			sql = "SELECT goods.`name` FROM goods";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				String name = resultSet.getString(1);
				options.add(name);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		goodsChooseComboBox.setItems(options);
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML 
	public void OnClickInquirePnoChooseComboBox(MouseEvent event) 
	{
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<String> options = FXCollections.observableArrayList();
		String sql = null;
		
		String text = inquireStateChooseComboBox.getValue();
		if (text.equals("待审核")) {
			sql = "SELECT purchase.pno FROM purchase WHERE purchase.state = " + 1;
		}
		else {
			sql = "SELECT purchase.pno FROM purchase WHERE purchase.state = " + 2;
		}
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				String pno = resultSet.getString(1);
				options.add(pno);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		inquirePnoChooseComboBox.setItems(options);
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML 
	public void OnClickEnsureModifyButton(ActionEvent event) 
	{
		String pno = modifyPnoChooseComboBox.getValue();
		
		if (pno == null) {
			JOptionPane.showMessageDialog(null, "未选择订单，请重新选择！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;

		try {
			statement = connection.createStatement();
			sql = "SELECT purchase.state "
					+ "FROM purchase "
					+ "WHERE purchase.pno = '"
					+ pno
					+ "'";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				int state = resultSet.getInt(1);
				if (state == 2) {
					modifyPurchaseTableView.setItems(null);
					modifyPnoChooseComboBox.setItems(null);
					JOptionPane.showMessageDialog(null, "该订单已经通过审核，无法修改！", "error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//删除原有进货包含
		try {
			statement = connection.createStatement();
			sql = "DELETE FROM purchase_include "
					+ "WHERE purchase_include.pno = '"
					+ pno
					+ "'";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//插入进货_包含
		ObservableList<PurchaseInformation> data = modifyPurchaseTableView.getItems();
		PurchaseInformation temp = null;
		String gno = null;
		String amount = null;
		
		System.out.println(modifyPurchaseTableView.getItems().size());
		Iterator<PurchaseInformation> iterator = data.iterator();
		while (iterator.hasNext()) {
			temp = iterator.next();
			gno = temp.getGno();
			amount = temp.getAmount();
			try {
				statement = connection.createStatement();
				sql = "INSERT INTO purchase_include VALUES('"
						+ pno
						+ "', '"
						+ gno
						+ "', "
						+ amount
						+ ")";
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (inquirePno.equals(pno)) {
			inquirePno = "";
		}
		
		modifyPno = "";
		modifyPurchaseTableView.setItems(null);
		modifyPnoChooseComboBox.setItems(null);
		
		JOptionPane.showMessageDialog(null, "修改进货单成功！", "success", JOptionPane.INFORMATION_MESSAGE);
	}

	@FXML 
	public void OnClickCancelPurchaseButton(ActionEvent event) 
	{
		String pno = modifyPnoChooseComboBox.getValue();
		if (pno == null) {
			JOptionPane.showMessageDialog(null, "未选择订单，请重新选择！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		ObservableList<PurchaseInformation> data = FXCollections.observableArrayList();
		modifyPurchaseTableView.setItems(data);
		modifyPnoChooseComboBox.setValue(null);
		
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;

		try {
			statement = connection.createStatement();
			sql = "SELECT purchase.state "
					+ "FROM purchase "
					+ "WHERE purchase.pno = '"
					+ pno
					+ "'";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				int state = resultSet.getInt(1);
				if (state == 2) {
					JOptionPane.showMessageDialog(null, "该订单已经通过审核，无法取消！", "error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			statement = connection.createStatement();
			sql = "DELETE FROM purchase "
					+ "WHERE purchase.pno = '"
					+ pno
					+ "'";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML 
	public void OnClickDeleteModifyButton(ActionEvent event) 
	{
		ObservableList<PurchaseInformation> data = modifyPurchaseTableView.getItems();
		ObservableList<PurchaseInformation> newdata = FXCollections.observableArrayList();

		int id = 1;
		String amount;
		String gno;
		String name;
		String place;
		String factory;
		
		Iterator<PurchaseInformation> iterator = data.iterator();
		while (iterator.hasNext()) {
			PurchaseInformation temp = iterator.next();
			if(!temp.getMyCheckBox().isSelected())
			{
				amount = temp.getAmount();
				gno = temp.getGno();
				name = temp.getName();
				place = temp.getPlace();
				factory = temp.getFactory();
				newdata.add(new PurchaseInformation(id, gno, name, place, factory, amount));
				id++;
			}
		}

		modifyPurchaseTableView.setItems(newdata);
	}

	@FXML 
	public void OnClickModifyPnoChooseComboBox(MouseEvent event) 
	{
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<String> options = FXCollections.observableArrayList();
		String sql = null;
		
		//只能修改自己的
		sql = "SELECT purchase.pno FROM purchase WHERE purchase.state = 1 AND purchase.provide_eno = '"
				+ main.getEno()
				+ "'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				String pno = resultSet.getString(1);
				options.add(pno);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		modifyPnoChooseComboBox.setItems(options);
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML 
	public void OnActionInquirePnoChooseComboBox(ActionEvent event)
	{
		String pno = inquirePnoChooseComboBox.getValue();
		if (pno == null) {
			return;
		}
		if (pno.equals(inquirePno)) {
			return;
		}
		
		inquirePno = pno;

		
		ObservableList<InquirePurchaseInformation> data = FXCollections.observableArrayList();
		
		if (pno == null 
				|| pno.equals("")) {
			JOptionPane.showMessageDialog(null, "未选择进货单号，请选择！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//更新TableView
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;
		
		Integer id = 1;
		Integer amount;
		String gno = "";
		String name = "";
		String place = "";
		String factory = "";
		
		try {
			statement = connection.createStatement();
			sql = "SELECT purchase_include.gno, goods.`name`, goods.place, "
					+ "goods.factory, purchase_include.amount "
					+ "FROM purchase_include, goods "
					+ "WHERE purchase_include.pno = '"
					+ pno
					+ "' AND purchase_include.gno = goods.gno";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				gno = resultSet.getString(1);
				name = resultSet.getString(2);
				place = resultSet.getString(3);
				factory = resultSet.getString(4);
				amount = resultSet.getInt(5);
				data.add(new InquirePurchaseInformation(id, gno, name, place, factory, amount));
				id += 1;
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inquirePurchaseTableView.setItems(data);
	}
	
	@FXML 
	public void OnActionModifyPnoChooseComboBox(ActionEvent event)
	{
		String pno = modifyPnoChooseComboBox.getValue();
		if (pno == null) {
			return;
		}
		if (pno.equals(modifyPno)) {
			return;
		}
		
		modifyPno = pno;
		
		ObservableList<PurchaseInformation> data = FXCollections.observableArrayList();
//		
//		if (pno == null 
//				|| pno.equals("")) {
//			JOptionPane.showMessageDialog(null, "未选择进货单号，请选择！", "error", JOptionPane.INFORMATION_MESSAGE);
//			return;
//		}
		
		//更新TableView
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;
		
		Integer id = 1;
		Integer amount;
		String gno = "";
		String name = "";
		String place = "";
		String factory = "";
		
		try {
			statement = connection.createStatement();
			sql = "SELECT purchase_include.gno, goods.`name`, goods.place, "
					+ "goods.factory, purchase_include.amount "
					+ "FROM purchase_include, goods "
					+ "WHERE purchase_include.pno = '"
					+ pno
					+ "' AND purchase_include.gno = goods.gno";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resultSet.next()) {
				gno = resultSet.getString(1);
				name = resultSet.getString(2);
				place = resultSet.getString(3);
				factory = resultSet.getString(4);
				amount = resultSet.getInt(5);
				String tempstring = amount.toString();
				data.add(new PurchaseInformation(id, gno, name, place, factory, tempstring));
				id += 1;
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modifyPurchaseTableView.setItems(data);
	}

}
