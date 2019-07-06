package usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;

public class UsuarioDAO extends DAO <Usuario> {

	public void salvar(Usuario usuario) {

		try {
			
			String sql = "insert into tbusuarios (nome, login, senha) values (?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getLogin());
			pstm.setString(3, usuario.getSenha());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> listar() {

		List<Usuario> usuarios = new ArrayList<>();

		try {
			String sql = "SELECT * FROM tbusuarios";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));

				usuarios.add(usuario);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return usuarios;
	}

	public void remover(int id) {

		try {
			
			String sql = "delete from tbusuarios where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Usuario usuario) {

		try {
			
			String sql = "update tbusuarios set nome = ?, login = ?, senha = ? where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getLogin());
			pstm.setString(3, usuario.getSenha());
			pstm.setInt(4, usuario.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
