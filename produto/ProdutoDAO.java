package produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;

public class ProdutoDAO extends DAO <Produto> {

	public void salvar(Produto produto) {

		try {
			String sql = "insert into tbprodutos (nome, quantidade, dataValidade) values (?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setInt(2, produto.getQuantidade());
			pstm.setDate(3, new java.sql.Date(produto.getDataValidade()
					.getTime()));
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> listar() {

		List<Produto> produtos = new ArrayList<>();

		try {
			String sql = "SELECT * FROM tbprodutos";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setDataValidade(rs.getDate("dataValidade"));

				produtos.add(produto);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return produtos;

	}

	public void remover(int id) {

		try {
			String sql = "delete from tbprodutos where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void atualizar(Produto produto) {

		try {

			String sql = "update tbprodutos set nome = ?, quantidade = ?,"+ " dataValidade = ? where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setInt(2, produto.getQuantidade());
			pstm.setDate(3, new java.sql.Date(produto.getDataValidade().getTime()));	
			pstm.setInt(4, produto.getId());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}



}
