package com.upload;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
public class DBUtils {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public DBUtils() {

		try {
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream("config.properties"));
			Class.forName(properties.getProperty("driverName"));

			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");

			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("db connection error.");
		}

	}

	public List query(String sql, Object... objects) {
		ArrayList list = new ArrayList();

		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				pstmt.setObject(i + 1, objects[i]);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int coutColumn = rsmd.getColumnCount();

			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 0; i < coutColumn; i++) {
					map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("db close error.");
		}
		;
	}
}
