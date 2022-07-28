package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.dao.ConexionDb;
import gt.gob.mineco.diaco.util.ValidateFacData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class ValidaFacturaImp implements ValidaFactura{

    public boolean validarFacturaServ(ValidateFacData validateData){
        boolean regresa = false;

        String sql = "SELECT count(q.id_queja) cantidad FROM diaco_queja q "  +
                           " INNER JOIN diaco_proveedor p  "  +
                               " on p.id_proveedor = q.id_proveedor "  +
                            " WHERE p.nit_proveedor = ? and q.factura_numero = ?";
        ConexionDb db = new ConexionDb();
		Connection connection = db.getConnection();
        ResultSet rs = null;
		CallableStatement cs = null;


        try {
			cs = connection.prepareCall(sql);
			cs.setString(1, validateData.getNit());
			cs.setString(2, validateData.getCorrelativo());
			rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println("Rs " + rs.getString(1));
                if(rs.getInt(1) > 0){
                    regresa = true;
                    
                }else{
                    
                    regresa = false;
                }

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(connection, null, cs);
		}

        return regresa;
    }
    
}
