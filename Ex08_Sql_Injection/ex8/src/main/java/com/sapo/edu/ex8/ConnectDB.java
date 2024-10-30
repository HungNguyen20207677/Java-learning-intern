package com.sapo.edu.ex8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private String userName = "root" ;

    private String password = "Hung@0984705610";

    private String url = "jdbc:mysql://localhost:3306/exercise8";

    public Connection connect() {
        //Tạo đối tượng Connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}