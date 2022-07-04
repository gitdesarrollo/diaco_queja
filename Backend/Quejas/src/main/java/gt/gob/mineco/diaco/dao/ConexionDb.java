package gt.gob.mineco.diaco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionDb  implements Serializable{

    private Context ctx = null;
    private DataSource ds = null;
    private Connection connection = null;

    public ConexionDb(){}

    public Connection getConnection(){
        try {
            this.ctx = null;
            this.ds = null;
            this.ctx = new InitialContext();
            this.ds = (DataSource) this.ctx.lookup("jdbc/DEVds");
            this.connection = this.ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return this.connection;

    }

    private void close(Connection connection, ResultSet rs, Statement stmt){
        if(rs != null){
            try {
                if(!rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                if(!stmt.isClosed()){
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
