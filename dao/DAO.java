package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import mysql.MysqlSingleton;

public abstract class DAO <T> {

	protected Connection conn;
	protected PreparedStatement pstm;
	
	public DAO(){
		conn = MysqlSingleton.getConnection();
	}
	
	public abstract void salvar(T obj);
	public abstract List<T> listar();
	public abstract void atualizar (T obj);
	public abstract void remover (int id);
	
	
	public void fecharConexao() {

		try {
			pstm.close();
			conn.close();

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
}
