package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectSqlDB {

    public static Connection connection = null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet resultSet = null;

    public static Properties loadLocalProperties() throws IOException {
        Properties prop = new Properties();
        InputStream ism = new FileInputStream("src/local-secret.properties");
        prop.load(ism);
        ism.close();

        return prop;
    }
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = loadLocalProperties();
        String driver = properties.getProperty("MYSQLJDBC.driver");
        String url = properties.getProperty("MYSQLJDBC.url");
        String userName = properties.getProperty("MYSQLJDBC.userName");
        String password = properties.getProperty("MYSQLJDBC.password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url,userName,password);
        System.out.println("Database is connected");
        return connection;

    }

    public static List<Student> readDataBase(String tableName) throws SQLException, IOException, ClassNotFoundException {
        List<Student> dataList = new ArrayList<Student>();
        try {
            getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select *From " + tableName);

            Student student = null;
            while (resultSet.next()) {
                String stName = resultSet.getString("stName");
                String stID = resultSet.getString("stID");
                String stDOB = resultSet.getString("stDOB");
                student = new Student(stName, stID, stDOB);
                dataList.add(student);
            }
        }catch (Exception ex){

        }
        return dataList;
    }


    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        List<Student> readStudentTable = readDataBase("Students");
        for(Student st:readStudentTable){
            System.out.println(st.getStName()+ " "+st.getStID()+ " "+st.getStDOB());
        }
    }
}
