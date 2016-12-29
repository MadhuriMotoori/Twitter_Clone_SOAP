package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnector {

	private ArrayList<Connection> connectionPool;
	private Connection conn = null;

	// JDBC driver name and database URL
	private static final String	DRIVER	= "com.mysql.jdbc.Driver";
	private static final String	DB_URL	= "jdbc:mysql://localhost/twitterAppDatabase?autoReconnect=true&useSSL=false";

	// Database credentials
	static final String	USER	= "root";
	static final String	PASS	= "mad";

	public DBConnector(){
		connectionPool = new ArrayList<Connection>();
	}
	
	public Connection getConnection2 () {
		try {
			// STEP 2: Register JDBC driver
			Class.forName(DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch ( SQLException se ) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch ( Exception e ) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return conn;
	}

	public void close () {
		
		returnConnection(conn);

	}
	
	
	public Connection getConnection () {
		 return (getPoolConnection());

	}

	public void createConnectionPool(int num) {
		
		
		if(connectionPool.size() == 0) {
			
			for(int i =0; i< num; i++) {
				connectionPool.add(getConnectionObject());
			}
		}
	}

	public Connection getConnectionObject () {
		try {
			// STEP 2: Register JDBC driver
			Class.forName(DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch ( SQLException se ) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch ( Exception e ) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getPoolConnection() {
		
		if(getPoolSize() <= 0) {
			updateConnectionPool(3);
		}
		
		Connection connection = connectionPool.get(getPoolSize() -1);
		connectionPool.remove(getPoolSize() -1);
		return connection;
	}

	public int getPoolSize() {
		
		if(connectionPool != null){
			return connectionPool.size();
		} else {
			return 0;
		}
	}

	public void updateConnectionPool(int num) {
		if(connectionPool != null) {
			for(int i =0; i< num; i++) {
				connectionPool.add(getConnectionObject());
			}
		}	
	}

	public void returnConnection(Connection connectionObject){
		if(connectionPool != null) {
			connectionPool.add(connectionObject);
		}
	}

	public void terminateConnectionPool() {
		if(connectionPool != null) {
			for(int i =0 ; i < connectionPool.size(); i++){
				Connection conn = connectionPool.get(i);
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
}
