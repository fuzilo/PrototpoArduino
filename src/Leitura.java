import Controle.Conexao;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Leitura {
    private String dataAtual;
    private double temperatura;
    private double umidade;
    
    private Conexao con = new Conexao();

    public Leitura(double temperatura, double umidade) {
        this.dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.temperatura = temperatura;
        this.umidade = umidade;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void salvarLeitura() {
        String sql = "INSERT INTO leitura (data_atual, temperatura, umidade) VALUES ('" + dataAtual + "', " + temperatura + ", " + umidade + ")";
        System.out.println("Executando SQL: " + sql);
        con.executeSQL(sql);
        JOptionPane.showMessageDialog(null, "Leitura salva com sucesso!");
    }
}
