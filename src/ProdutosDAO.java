/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
  Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.execute();
            prep.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no  ao cadastrar: " + e.getMessage());
        }
    }
    public void venderProduto(int idProduto) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    conn = new conectaDAO().connectDB();

    try {
        prep = conn.prepareStatement(sql);
        prep.setInt(1, idProduto);
        prep.executeUpdate();
        prep.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }}
    public ArrayList<ProdutosDTO> listarProdutos(){
       String sql = "SELECT * FROM produtos";

        conn = new conectaDAO().connectDB();
        lista.clear(); // ?evita duplicar dados

        try {
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no ProdutosDAO ao listar: " + e.getMessage());
        }

        return lista;
    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();

    conn = new conectaDAO().connectDB();

    try {
        prep = conn.prepareStatement(sql);
        rs = prep.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            listaVendidos.add(produto);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    }

    return listaVendidos;
}
    
}

    
        


