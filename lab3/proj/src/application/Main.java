package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private static final String driverName = "com.mysql.cj.jdbc.Driver";
	private static final String databaseURL = "jdbc:mysql://localhost:3306/sqllab?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
	private static final String user = "root";
	private static final String password = "";

	private Connection connection = null;
	private Stage stage;

	private String eno = "";

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(databaseURL, user, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (connection == null) {
			JOptionPane.showMessageDialog(null, "数据库连接失败！", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		MainSceneController mainSceneController = null;
		getStage().setTitle("手机仓库管理系统");
		changeScene("MainScene.fxml", mainSceneController, this);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		// super.finalize();
		connection.close();
	}

	public void changeScene(String fxmlName, SetProperty controller, Main main) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));

		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controller = loader.getController();
		controller.setMain(main);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		main.getStage().setScene(scene);
		main.getStage().show();
	}

	public Stage getStage() {
		return stage;
	}

	public Connection getConnction() {
		return connection;
	}

	public void setEno(String eno) {
		this.eno = eno;
	}

	public String getEno() {
		return eno;
	}
}
