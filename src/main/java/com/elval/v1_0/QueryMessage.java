package v1_0;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class QueryMessage {
    public QueryMessage() {
        super();
    }
    
    //public static NotificationType queryMsgToUsers(Connection con, String dbName) throws SQLException {
    public  NotificationType queryMsgToUsers() throws SQLException {
        NotificationType nt = new NotificationType();
        try{
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection conn=DriverManager.getConnection(  
            //"jdbc:oracle:thin:hr/hr@localhost:1521:xe","hr","hr"); 
			
			String DATASOURCE_CONTEXT = "jdbc/elval_DB";
            Connection conn = null;
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                conn = datasource.getConnection();
            } else {
                throw new SQLException();
            }
			
			
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select * from  LV3_MESSAGE_TO_USERS where START_DATE<=sysdate and END_DATE>=sysdate");
            while ( rs.next() ) {
                String msg = rs.getString("message");
                nt.setMessage(msg);
                Date sd = rs.getDate("start_date");
                nt.setStartDate(sd);
                Date ed = rs.getDate("end_date");
                nt.setEndDate(ed);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Connection DB problem! ");
            System.err.println(e.getMessage());
        }
        
        return nt;
}

    
}
