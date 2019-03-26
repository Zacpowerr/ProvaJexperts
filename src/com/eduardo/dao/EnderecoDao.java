package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.Endereco;
import com.eduardo.model.Usuario;

public class EnderecoDao extends Dao implements DaoI<Endereco> {

	@Override
		public boolean cadastrar(Endereco obj) {
			String sql = "insert into endereco(rua,numero,complemento,bairro,cidade,estado,cep) values(?,?,?,?,?,?,?);";
			try {
				PreparedStatement stmt;
				stmt = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	
				stmt.setString(1, obj.getRua());
				stmt.setInt(2, obj.getNumero());
				stmt.setString(3, obj.getComplemento());
				stmt.setString(4, obj.getBairro());
				stmt.setString(5, obj.getCidade());
				stmt.setString(6, obj.getEstado());
				stmt.setInt(7, obj.getCep());
	
				stmt.executeUpdate();
				ResultSet res = stmt.getGeneratedKeys();
	            res.next();
	            obj.setId(res.getInt(1));
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

	@Override
	public boolean alterar(Endereco obj) {
String sql = "update from endereco rua = ?,numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ?,cep = ? where id = ?";
		
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, obj.getRua());
			stmt.setInt(2, obj.getNumero());
			stmt.setString(3, obj.getComplemento());
			stmt.setString(4, obj.getBairro());
			stmt.setString(5, obj.getCidade());
			stmt.setString(6, obj.getEstado());
			stmt.setInt(7, obj.getCep());
			stmt.setInt(8, obj.getId());
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletar(Endereco obj) {
String sql = "delete endereco where id = ?";
		
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
	public List<Endereco> pesquisar() {
		String sql = "select e.rua,e.numero,e.complemento,e.cep from endereco as e;";
		int cont=0;
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Endereco> listEndereco = new ArrayList<>();
			while(result.next()) {
				
				Endereco e = new Endereco();
				e.setRua(result.getString("e.rua"));
				e.setNumero(result.getInt("e.numero"));
				e.setComplemento(result.getString("e.complemento"));
				e.setCep(result.getInt("e.cep"));
				cont++;
				listEndereco.add(e);
				
			}
			System.out.println("Endereco:"+cont);
			return listEndereco;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public int pegaridMAX() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT MAX(id) as id FROM endereco");
			ResultSet result = stmt.executeQuery();
			Endereco endereco = new Endereco();
			if (result.next()) {

				endereco.setId(result.getInt("id"));
			}
			return endereco.getId();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}

	}

}
