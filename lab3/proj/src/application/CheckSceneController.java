package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class CheckSceneController extends SetProperty{
	@FXML
	private ComboBox<String> typeComboBox;
	@FXML
	private ComboBox<String> numberComboBox;
	
	@FXML
	private TableView<CheckInformation> checkTableView;
	@FXML
	private TableColumn<CheckInformation, Integer> idCol;
	@FXML
	private TableColumn<CheckInformation, String> gnoCol;
	@FXML
	private TableColumn<CheckInformation, String> nameCol;
	@FXML
	private TableColumn<CheckInformation, String> placeCol;
	@FXML
	private TableColumn<CheckInformation, String> factoryCol;
	@FXML
	private TableColumn<CheckInformation, Integer> amountCol;
	@FXML
	private Button checkPassButton;
	
	private String lastNum = "";
	@FXML TableView<CheckInformation> goodsTableView;
	@FXML TableColumn<CheckInformation, Integer> goodsIdCol;
	@FXML TableColumn<CheckInformation, String> goodsGnoCol;
	@FXML TableColumn<CheckInformation, String> goodsNameCol;
	@FXML TableColumn<CheckInformation, String> goodsPlaceCol;
	@FXML TableColumn<CheckInformation, String> goodsFactoryCol;
	@FXML TableColumn<CheckInformation, Integer> goodsAmountCol;
	@FXML Button inquireGoodsButton;
	
	// Event Listener on ComboBox[#numberComboBox].onMouseClicked
	@FXML
	public void OnClickNumberComboBox(MouseEvent event) {
		// TODO Autogenerated
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<String> options = FXCollections.observableArrayList();
		String sql = null;
		
		String type = typeComboBox.getValue();
		
		if (type.equals("进货单")) {
			sql = "SELECT purchase.pno FROM purchase WHERE purchase.state = 1";
		}
		else {
			sql = "SELECT shipping.sno FROM shipping WHERE state = 1";
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
				String Num = resultSet.getString(1);
				options.add(Num);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		numberComboBox.setItems(options);
		
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void OnActionNumberComboBox(ActionEvent event)
	{
		String type = typeComboBox.getValue();
		
		String Num = numberComboBox.getValue();
		if (Num == null) {
			return;
		}
		if (Num.equals(lastNum)) {
			return;
		}
		
		lastNum = Num;
		
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
		
		ObservableList<CheckInformation> data = FXCollections.observableArrayList();
		
		
		try {
			statement = connection.createStatement();
			if (type.equals("进货单")) {
				sql = "SELECT purchase_include.gno, goods.`name`, goods.place, "
						+ "goods.factory, purchase_include.amount "
						+ "FROM purchase_include, goods "
						+ "WHERE purchase_include.pno = '"
						+ Num
						+ "' AND purchase_include.gno = goods.gno";
			}
			else {
					sql = "SELECT shipping_include.gno, goods.`name`, goods.place, goods.factory, "
							+ "shipping_include.amount FROM shipping_include, goods "
							+ "WHERE shipping_include.sno = '"
							+ Num
							+ "' AND shipping_include.gno = goods.gno";
			}
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
				data.add(new CheckInformation(id, gno, name, place, factory, amount));
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
		
		checkTableView.setItems(data);
	}
	
	@Override
	void setMain(Main main) {
		// TODO Auto-generated method stub
		super.setMain(main);
		myinitialize();
	}
	
	public void myinitialize() {
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		factoryCol.setCellValueFactory(new PropertyValueFactory<>("factory"));
		gnoCol.setCellValueFactory(new PropertyValueFactory<>("gno"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));
		
		goodsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		goodsAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		goodsFactoryCol.setCellValueFactory(new PropertyValueFactory<>("factory"));
		goodsGnoCol.setCellValueFactory(new PropertyValueFactory<>("gno"));
		goodsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		goodsPlaceCol.setCellValueFactory(new PropertyValueFactory<>("place"));
		
		ObservableList<String> options = FXCollections.observableArrayList("进货单", "出货单");
		typeComboBox.setItems(options);
		
		typeComboBox.setValue("进货单");
	}

	@FXML 
	public void OnClickCheckPassButton(ActionEvent event) 
	{
		String type = typeComboBox.getValue();
		String Num = numberComboBox.getValue();
		
		if (Num == null) {
			JOptionPane.showMessageDialog(null, "未选择货单，请重新选择！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;

		try {
			statement = connection.createStatement();
			if (type.equals("进货单")) {
				sql = "SELECT purchase.state "
						+ "FROM purchase "
						+ "WHERE purchase.pno = '"
						+ Num
						+ "'";
			}
			else {
				sql = "SELECT state FROM shipping WHERE sno = '"
						+ Num
						+ "'";
			}
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				int state = resultSet.getInt(1);
				if (state != 1) {
					JOptionPane.showMessageDialog(null, "该货单已经通过审核！", "error", JOptionPane.INFORMATION_MESSAGE);
					checkTableView.setItems(null);
					numberComboBox.setItems(null);
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
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//修改状态
		if (type.equals("进货单")) {
			try {
				sql = "UPDATE purchase SET state = 2, check_eno = '"
						+ main.getEno()
						+ "' WHERE pno = '"
						+ Num
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
		else {
			try {
					sql = "SELECT shipping_include.amount, goods.amount "
							+ "FROM shipping_include, goods "
							+ "WHERE shipping_include.sno = '"
							+ Num
							+ "' AND shipping_include.gno = goods.gno";
				resultSet = statement.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int need;
			int have;
			boolean flag = true;
			
			try {
				
				while (resultSet.next()) {
					need = resultSet.getInt(1);
					have = resultSet.getInt(2);
					flag = flag && (need <= have);
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
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if (flag) {
				sql = "UPDATE shipping SET state = 3, check_eno = '"
						+ main.getEno()
						+ "' WHERE sno = '"
						+ Num
						+ "'";
				JOptionPane.showMessageDialog(null, "审核货单成功！", "success", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				sql = "UPDATE shipping SET state = 2, check_eno = '"
						+ main.getEno()
						+ "' , shipping.register_time = CURRENT_TIME WHERE sno = '"
						+ Num
						+ "'";
				JOptionPane.showMessageDialog(null, "货单缺货登记！", "success", JOptionPane.INFORMATION_MESSAGE);
			}
			
			try {
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

		checkTableView.setItems(null);
		numberComboBox.setItems(null);
	}

	@FXML 
	public void OnClickInquireGoodsButton(ActionEvent event) 
	{
		ObservableList<CheckInformation> data = FXCollections.observableArrayList();
		
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
			sql = "SELECT * FROM goods";
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
				amount = resultSet.getInt(4);
				factory = resultSet.getString(5);
				data.add(new CheckInformation(id, gno, name, place, factory, amount));
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
		
		goodsTableView.setItems(data);
	}
}
