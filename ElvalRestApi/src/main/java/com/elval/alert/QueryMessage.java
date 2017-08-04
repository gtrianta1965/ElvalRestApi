package com.elval.alert;

import java.sql.*;

import java.util.logging.Logger;

import javax.sql.*;

import javax.naming.*;

public class QueryMessage {
    static final String DATASOURCE_CONTEXT = "jdbc/elvalDB";
    static final String ACTIVE_NOTIFICATIONS_SQL = "select * from  LV3_MESSAGE_TO_USERS where START_DATE<=sysdate and END_DATE>=sysdate";

    private static final Logger LOGGER = Logger.getLogger( QueryMessage.class.getName() );
    
    public QueryMessage() {
        super();
    }

    public NotificationType queryActiveNotifications() {
        NotificationType nt = new NotificationType();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        LOGGER.info("queryActiveNotifications called");
        try {
            Context initialContext = new InitialContext();
            DataSource datasource =
                (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                conn = datasource.getConnection();
            } else {
                throw new SQLException();
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(ACTIVE_NOTIFICATIONS_SQL);
            while (rs.next()) {
                nt.setMessage(rs.getString("message"));
                nt.setStartDate(rs.getDate("start_date"));
                nt.setEndDate(rs.getDate("end_date"));
                nt.setPeriod(rs.getInt("period"));
                //we only interested in one record so break
                break;
            }

        } catch (SQLException sq) {
            System.out.println((sq.getMessage()));
            nt.setError(sq.getMessage());
        } catch (Exception e) {
            nt.setError(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sq) {
                nt.setError("Error in finally:" + sq.getMessage());
                System.out.println(sq.getMessage());
            }
        }

        return nt;
    }


}
