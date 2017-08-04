package alertsCode;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class QueryMessage {
    public QueryMessage() {
        super();
    }
    
	public NotificationType queryMsgToUsers(){
		NotificationType nt = new NotificationType();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(
            "jdbc:oracle:thin:hr/hr@localhost:1521:xe","hr","hr");

            //String DATASOURCE_CONTEXT = "jdbc/elvalDB";

            //Context initialContext = new InitialContext();
            //DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            //if (datasource != null) {
            //    conn = datasource.getConnection();
            //} else {
            //    throw new SQLException();
            //}
            stmt = conn.createStatement();
            rs =
                stmt.executeQuery("select * from  LV3_MESSAGE_TO_USERS where START_DATE<=sysdate and END_DATE>=sysdate");
            while (rs.next()) {
                String msg = rs.getString("message");
                nt.setMessage(msg);
                Date sd = rs.getDate("start_date");
                nt.setStartDate(sd);
                Date ed = rs.getDate("end_date");
                nt.setEndDate(ed);
                int p = rs.getInt("period");
                nt.setPeriod(p);
            }

        } catch (SQLException sq) {
            System.err.println(sq.getMessage());
        } catch (Exception e) {
            System.err.println("Connection DB problem! ");
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                    stmt.close();
                    rs.close();
                }
            } catch (SQLException sq) {
                System.err.println(sq.getMessage());
            }
        }

        return nt;
    }

    
}
