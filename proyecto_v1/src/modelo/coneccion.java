package modelo;

import java.sql.*;

public class coneccion {

	private Connection cone;
	private Statement st;

	public Connection getCone() {
		return cone;
	}

	public void setCone(Connection cone) {
		this.cone = cone;
	}

	public coneccion() {
		iniciar_con();
	}

	public String iniciar_con() {
		String fun = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String BaseDatos = "jdbc:oracle:thin:@172.17.42.63:1521:orclupsoltp";
			cone = DriverManager.getConnection(BaseDatos, "p54g1_oltp_df", "g01_4263");
			if (cone != null) {
				fun = "Conexion exitosa";
			} else {
				fun = "conexion fallida";
			}
		} catch (Exception e) {
			System.out.println("Error sql");
			e.printStackTrace();
		}
		return fun;
	}

	public ResultSet Consulta(String sql) {
		String res = "";
		ResultSet rs = null;
		System.out.println("-----1");
		try {
			st = cone.createStatement();
			rs = st.executeQuery(sql);
			res = "Consulta Completada";
			System.out.println(res);
		} catch (SQLException e) {
			res = "Error al Consultar";
			e.printStackTrace();
			System.out.println(res);
		}
		return rs;
	}

	public String Ejecutar(String sql) {
		String resul = "";
		try {
			st = getCone().createStatement();
			st.execute(sql);
			st.close();
			resul = "Datos Ingresados";
		} catch (SQLException e) {
			e.printStackTrace();
			resul = "Error al insertar Datos";
		}
		return resul;
	}

	public void fin_cone() {
		try {
			cone.close();
			System.out.println("Coneccion Finalizada ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
