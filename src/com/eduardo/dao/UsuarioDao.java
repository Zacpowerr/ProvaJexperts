package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.Usuario;

public class UsuarioDao extends Dao implements DaoI<Usuario> {

	@Override
	public boolean cadastrar(Usuario obj) {

		String sql = "insert into usuario(nome,email,telefone,cargo,login,senha,cpf,superior,idEndereco) values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getEmail());
			stmt.setInt(3, obj.getTelefone());
			stmt.setString(4, obj.getCargo());
			stmt.setString(5, obj.getLogin());
			stmt.setString(6, obj.getSenha());
			stmt.setString(7, obj.getCpf());
			stmt.setString(8, obj.getSuperior());
			stmt.setInt(9, obj.getIdEndereco());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean alterar(Usuario obj) {
		String sql = "update usuario set nome = ?,email = ?,telefone = ?,cargo = ?,login = ?,senha = ?,cpf = ?,superior = ?,idEndereco = ? where id = ?";

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getEmail());
			stmt.setInt(3, obj.getTelefone());
			stmt.setString(4, obj.getCargo());
			stmt.setString(5, obj.getLogin());
			stmt.setString(6, obj.getSenha());
			stmt.setString(7, obj.getCpf());
			stmt.setString(8, obj.getSuperior());
			stmt.setInt(9, obj.getIdEndereco());
			stmt.setInt(10, obj.getId());
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletar(Usuario obj) {
		String sql = "delete usuario where id = ?";

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Usuario> pesquisar() {
		String sql = "select u.nome,u.cargo,u.telefone,u.email,u.cpf,u.login,u.senha,e.rua,e.numero,e.complemento,e.cep from usuario as u "
				+ "inner join endereco as e on u.idEndereco = e.id; ";

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Usuario> listUsuario = new ArrayList<>();

			while (result.next()) {
				Usuario u = new Usuario();
				u.setNome(result.getString("nome"));
				u.setEmail(result.getString("email"));
				u.setTelefone(result.getInt("telefone"));
				u.setCargo(result.getString("cargo"));
				u.setLogin(result.getString("login"));
				u.setSenha(result.getString("senha"));
				u.setCpf(result.getString("cpf"));

				u.setRua(result.getString("e.rua"));
				u.setNumero(result.getInt("e.numero"));
				u.setComplemento(result.getString("e.complemento"));
				u.setCep(result.getInt("e.cep"));

				listUsuario.add(u);

			}

			return listUsuario;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
public List<Usuario> pesquisarCB(){
	String sql = "select u.id,u.nome,u.cargo,u.telefone,u.email,u.cpf,u.login,u.senha,e.rua,e.numero,e.complemento,e.cep from usuario as u "
			+ "inner join endereco as e on u.idEndereco = e.id; ";

	try {
		PreparedStatement stmt;
		stmt = conexao.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		List<Usuario> listUsuario = new ArrayList<>();

		while (result.next()) {
			Usuario u = new Usuario();
			u.setId(result.getInt("id"));
			u.setNome(result.getString("nome"));
			u.setEmail(result.getString("email"));
			u.setTelefone(result.getInt("telefone"));
			u.setCargo(result.getString("cargo"));
			u.setLogin(result.getString("login"));
			u.setSenha(result.getString("senha"));
			u.setCpf(result.getString("cpf"));

			u.setRua(result.getString("e.rua"));
			u.setNumero(result.getInt("e.numero"));
			u.setComplemento(result.getString("e.complemento"));
			u.setCep(result.getInt("e.cep"));

			listUsuario.add(u);

		}

		return listUsuario;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

	public List<Usuario> pesquisarFiltro(String string, int i) {
		String sql = "select u.nome,u.cargo,u.telefone,u.email,u.cpf,u.login,u.senha,e.rua,e.numero,e.complemento,e.cep from usuario as u "
				+ "inner join endereco as e on u.idEndereco = e.id where superior = ? and  u.id <> ?; ";

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, string);
			stmt.setInt(2, i);
			ResultSet result = stmt.executeQuery();
			List<Usuario> listUsuario = new ArrayList<>();

			while (result.next()) {
				Usuario u = new Usuario();
				u.setNome(result.getString("nome"));
				u.setEmail(result.getString("email"));
				u.setTelefone(result.getInt("telefone"));
				u.setCargo(result.getString("cargo"));
				u.setLogin(result.getString("login"));
				u.setSenha(result.getString("senha"));
				u.setCpf(result.getString("cpf"));

				u.setRua(result.getString("e.rua"));
				u.setNumero(result.getInt("e.numero"));
				u.setComplemento(result.getString("e.complemento"));
				u.setCep(result.getInt("e.cep"));

				listUsuario.add(u);

			}

			return listUsuario;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
