package com.eduardo.control;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.eduardo.dao.EnderecoDao;
import com.eduardo.dao.UsuarioDao;
import com.eduardo.model.Endereco;
import com.eduardo.model.Usuario;

public class UsuarioControl {

	private JTextField tfNome;
	private JTextField tfTel;
	private JTextField tfEmail;
	private JTextField tfCpf;
	private JTextField tfLogin;
	private JPasswordField tfSenha;
	private JTextField tfRua;
	private JTextField tfNumero;
	private JTextField tfComplemento;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JTextField tfEstado;
	private JTextField tfCep;
	private JComboBox cbCargo;
	private JComboBox cbSuperior;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private Endereco endereco;
	private EnderecoDao enderecoDao;

	private JTable table;
	private JComboBox comboBox;
	private List<Usuario> listusuario;
	private List<Endereco> listendereco;

	public UsuarioControl(JTable table, JComboBox comboBox) {
		super();
		this.table = table;
		this.comboBox = comboBox;
		this.usuario = new Usuario();
		this.usuarioDao = new UsuarioDao();
		this.enderecoDao = new EnderecoDao();
	}

	public UsuarioControl(JTextField tfNome, JTextField tfTel, JTextField tfEmail, JTextField tfCpf, JTextField tfLogin,
			JPasswordField tfSenha, JTextField tfRua, JTextField tfNumero, JTextField tfComplemento, JTextField tfBairro,
			JTextField tfCidade, JTextField tfEstado, JTextField tfCep, JComboBox cbCargo, JComboBox cbSuperior,
			Usuario usuario, UsuarioDao usuarioDao) {
		super();
		this.tfNome = tfNome;
		this.tfTel = tfTel;
		this.tfEmail = tfEmail;
		this.tfCpf = tfCpf;
		this.tfLogin = tfLogin;
		this.tfSenha = tfSenha;
		this.tfRua = tfRua;
		this.tfNumero = tfNumero;
		this.tfComplemento = tfComplemento;
		this.tfBairro = tfBairro;
		this.tfCidade = tfCidade;
		this.tfEstado = tfEstado;
		this.tfCep = tfCep;
		this.cbCargo = cbCargo;
		this.cbSuperior = cbSuperior;
		this.usuario = new Usuario();
		this.usuarioDao = new UsuarioDao();
		this.enderecoDao = new EnderecoDao();
	}

	public int pegarIdAction() {

		try {
			return enderecoDao.pegaridMAX();

		} catch (Exception e) {
			return 0;
		}

	}

	public void salvarAction() {
		usuario = new Usuario();
		endereco = new Endereco();
		if (tfNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preecha o campo Nome", "erro", 0);
			return;
		}
		usuario.setNome(tfNome.getText());
		usuario.setCpf(tfCpf.getText());
		if (tfEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preecha o campo Email", "erro", 0);
			return;
		}
		usuario.setEmail(tfEmail.getText());
		usuario.setTelefone(Integer.parseInt(tfTel.getText()));
		if (cbCargo.getSelectedIndex() != 0) {
			if (cbCargo.getSelectedItem().equals("Diretor") && cbSuperior.getSelectedItem().equals("Diretor")) {
				usuario.setCargo((String) cbCargo.getSelectedItem());
				usuario.setSuperior((String) cbSuperior.getSelectedItem());
			} else if (cbCargo.getSelectedItem().equals("Gerente") && cbSuperior.getSelectedItem().equals("Gerente")
					|| cbSuperior.getSelectedItem().equals("Diretor")) {
				usuario.setCargo((String) cbCargo.getSelectedItem());
				usuario.setSuperior((String) cbSuperior.getSelectedItem());
			} else if (cbCargo.getSelectedItem().equals("Consultor") && cbSuperior.getSelectedItem().equals("Gerente")
					|| cbSuperior.getSelectedItem().equals("Diretor")) {
				usuario.setCargo((String) cbCargo.getSelectedItem());
				usuario.setSuperior((String) cbSuperior.getSelectedItem());

			} else {
				JOptionPane.showMessageDialog(null, "Cargo e Superior nao corresponde", "erro", 0);
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cargo", "erro", 0);
			return;
		}
		if (tfLogin.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preecha o campo Login", "erro", 0);
			return;
		}
		usuario.setLogin(tfLogin.getText());
		if (tfSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preecha o campo Senha", "erro", 0);
			return;
		}
		usuario.setSenha(tfSenha.getText());

		// Enderecos
		if (tfRua.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Rua", "erro", 0);
			return;
		}
		endereco.setRua(tfRua.getText());
		if (tfCep.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cep", "erro", 0);
			return;
		}
		endereco.setCep(Integer.parseInt(tfCep.getText()));
		if (tfBairro.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "erro", 0);
			return;
		}
		endereco.setBairro(tfBairro.getText());
		if (tfCidade.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "erro", 0);
			return;
		}
		endereco.setCidade(tfCidade.getText());
		endereco.setComplemento(tfComplemento.getText());
		if (tfNumero.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Número", "erro", 0);
			return;
		}
		endereco.setNunero(Integer.parseInt(tfNumero.getText()));
		if (tfEstado.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Estado", "erro", 0);
			return;
		}
		endereco.setEstado(tfEstado.getText());

		boolean res = enderecoDao.cadastrar(endereco);
		if (res) {
			System.out.println("cadastrado");
			usuario.setIdEndereco(pegarIdAction());
			System.out.println(usuario.getSuperior());
			res = usuarioDao.cadastrar(usuario);
			if (res) {
				JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso", "Cadastrado", 1
						);
			}
		}
	}

	public void popularCBPesquisa() {
		listusuario = usuarioDao.pesquisarCB();
		ComboBoxModel<Object> modelCombo;
		modelCombo = new DefaultComboBoxModel<Object>(listusuario.toArray());
		comboBox.setModel(modelCombo);
	}

	public void listarAction() {
		listusuario = usuarioDao.pesquisar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Usuario u : listusuario) {
			model.addRow(new Object[] { u.getNome(), u.getCargo(), u.getTelefone(), u.getEmail(), u.getCpf(),
					u.getLogin(), u.getSenha(), u.getRua(), u.getNumero(), u.getComplemento(), u.getCep() });

		}

	}

	public void listarAction(Usuario usuario) {
		System.out.println(usuario.getId());
		listusuario = usuarioDao.pesquisarFiltro(usuario.getCargo(),usuario.getId());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Usuario u : listusuario) {
			model.addRow(new Object[] { u.getNome(), u.getCargo(), u.getTelefone(), u.getEmail(), u.getCpf(),
					u.getLogin(), u.getSenha(), u.getRua(), u.getNumero(), u.getComplemento(), u.getCep() });

		}

	}

	public void limparAction() {
		String vazio = "";
		tfBairro.setText(vazio);
		tfCep.setText(vazio);
		tfCidade.setText(vazio);
		tfComplemento.setText(vazio);
		tfCpf.setText(vazio);
		tfEmail.setText(vazio);
		tfEstado.setText(vazio);
		tfLogin.setText(vazio);
		tfNome.setText(vazio);
		tfNumero.setText(vazio);
		tfRua.setText(vazio);
		tfSenha.setText(vazio);
		tfTel.setText(vazio);
		cbCargo.setSelectedIndex(0);
		cbSuperior.setSelectedIndex(0);
		
	}
}
