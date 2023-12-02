package dbarrientos.data;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	protected Connection connection = null;
	private static final String PROPERTIES_FILE_NAME = "dbarrientos/resources/GlobalData.properties";
	private static Map<String, String> DATABASE_PROPERTIES;

	public DataBase() {
		DATABASE_PROPERTIES = this.getProperties();
	}

	protected void connect() throws SQLException, ClassNotFoundException {
		String driver = DATABASE_PROPERTIES.get("driver");
		String server = DATABASE_PROPERTIES.get("server");
		String port = DATABASE_PROPERTIES.get("port");
		String user = DATABASE_PROPERTIES.get("user");
		String secret = DATABASE_PROPERTIES.get("password");
		String database = DATABASE_PROPERTIES.get("database");
		String engine = DATABASE_PROPERTIES.get("engine");
		String url = "jdbc:" + engine + "://" + server + ":" + port + "/" + database;
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, secret);
	}

	protected void disconnect() throws SQLException {
		if (!connection.isClosed()) {
			connection.close();
		}
	}

	private Map<String, String> getProperties() {
		Map<String, String> hashMap = new HashMap<>();
		try {
			Properties prop = new Properties();
			URL resourceUrl = getClass().getResource(PROPERTIES_FILE_NAME);
			File file = new File(resourceUrl.toURI());
			prop.load(new BufferedInputStream(new FileInputStream(file)));
			String driver = prop.getProperty("database_driver");
			String server = prop.getProperty("database_server");
			String port = prop.getProperty("database_port");
			String user = prop.getProperty("database_user");
			String password = prop.getProperty("database_password");
			String database = prop.getProperty("database_name");
			String engine = prop.getProperty("database_engine");
			hashMap.put("driver", driver);
			hashMap.put("server", server);
			hashMap.put("port", port);
			hashMap.put("user", user);
			hashMap.put("password", password);
			hashMap.put("database", database);
			hashMap.put("engine", engine);

			return hashMap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
