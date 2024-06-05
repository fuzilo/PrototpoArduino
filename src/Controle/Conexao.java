package Controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexao {
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://127.0.0.1/SensorData";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;

    public boolean conecta() {
        boolean result = true;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectou com o Banco de Dados");
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
            result = false;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com a fonte de dados: " + Fonte);
            result = false;
        }
        return result;
    }

    public void desconecta() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Banco fechado");
            }
        } catch (SQLException fecha) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o banco de dados: " + fecha);
        }
    }

    public void executeSQL(String sql) {
        if (conecta()) {
            try {
                statement = conexao.createStatement();
                statement.execute(sql);
                System.out.println("SQL executado: " + sql);
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Erro ao executar SQL: " + sqle.getMessage());
            } finally {
                desconecta();
            }
        }
    }

    public ResultSet RetornarResultset(String sql) {
        ResultSet resultSet = null;
        if (conecta()) {
            try {
                statement = conexao.createStatement();
                resultSet = statement.executeQuery(sql);
                resultSet.next();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao retornar resultset: " + e.getMessage());
            }
        }
        return resultSet;
    }
}
