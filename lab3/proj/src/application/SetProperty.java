package application;

import java.sql.Connection;


public class SetProperty{
	protected Main main;
	protected Connection connection; 
	
    void setConnection(Connection connection)
    {
    	this.connection = connection;
    }
    
	void setMain(Main main)
	{
		this.main = main;
		setConnection(main.getConnction());
	}
}
