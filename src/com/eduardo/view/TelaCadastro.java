package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PasswordView;

import com.eduardo.control.UsuarioControl;
import com.eduardo.dao.UsuarioDao;
import com.eduardo.model.Usuario;
import javax.swing.JPasswordField;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfTel;
	private JTextField tfEmail;
	private JTextField tfCpf;
	private JTextField tfLogin;
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
	private UsuarioControl usuarioControl;
	private Listagem listagem = null;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 329);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnListagem = new JMenu("Listagem");

		menuBar.add(mnListagem);

		JMenuItem mntmListagem = new JMenuItem("Listagem");
		mntmListagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				listagem = new Listagem();
				listagem.setVisible(true);
			}
		});
		mnListagem.add(mntmListagem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 14, 38, 14);
		contentPane.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(58, 11, 120, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(215, 14, 60, 14);
		contentPane.add(lblTelefone);

		tfTel = new JTextField();
		tfTel.setBounds(271, 11, 120, 20);
		contentPane.add(tfTel);
		tfTel.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(401, 14, 38, 14);
		contentPane.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setBounds(447, 11, 120, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(587, 14, 30, 14);
		contentPane.add(lblCpf);

		tfCpf = new JTextField();
		tfCpf.setBounds(627, 11, 120, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(401, 42, 38, 14);
		contentPane.add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setBounds(447, 39, 120, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(587, 42, 46, 14);
		contentPane.add(lblSenha);

		cbSuperior = new JComboBox();
		cbSuperior.setModel(new DefaultComboBoxModel(new String[] { "Selecione o superior", "Diretor", "Gerente" }));
		cbSuperior.setBounds(271, 39, 120, 20);
		contentPane.add(cbSuperior);

		JLabel lblSuperior = new JLabel("Superior:");
		lblSuperior.setBounds(215, 42, 60, 14);
		contentPane.add(lblSuperior);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(10, 42, 38, 14);
		contentPane.add(lblCargo);

		cbCargo = new JComboBox();
		cbCargo.setModel(
				new DefaultComboBoxModel(new String[] { "Selecione algo", "Diretor", "Gerente", "Consultor" }));
		cbCargo.setBounds(58, 39, 120, 20);
		contentPane.add(cbCargo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 95, 771, 1);
		contentPane.add(separator);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 110, 38, 14);
		contentPane.add(lblRua);

		tfRua = new JTextField();
		tfRua.setBounds(58, 107, 120, 20);
		contentPane.add(tfRua);
		tfRua.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(188, 110, 60, 14);
		contentPane.add(lblNmero);

		tfNumero = new JTextField();
		tfNumero.setBounds(244, 107, 60, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(314, 110, 89, 14);
		contentPane.add(lblComplemento);

		tfComplemento = new JTextField();
		tfComplemento.setBounds(401, 107, 120, 20);
		contentPane.add(tfComplemento);
		tfComplemento.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(145, 149, 38, 14);
		contentPane.add(lblBairro);

		tfBairro = new JTextField();
		tfBairro.setBounds(193, 146, 120, 20);
		contentPane.add(tfBairro);
		tfBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(333, 149, 60, 14);
		contentPane.add(lblCidade);

		tfCidade = new JTextField();
		tfCidade.setBounds(381, 146, 120, 20);
		contentPane.add(tfCidade);
		tfCidade.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(511, 149, 46, 14);
		contentPane.add(lblEstado);

		tfEstado = new JTextField();
		tfEstado.setBounds(555, 146, 30, 20);
		contentPane.add(tfEstado);
		tfEstado.setColumns(10);

		JLabel lblCep = new JLabel("Cep:");
		lblCep.setBounds(531, 110, 30, 14);
		contentPane.add(lblCep);

		tfCep = new JTextField();
		tfCep.setBounds(563, 107, 120, 20);
		contentPane.add(tfCep);
		tfCep.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 200, 771, 1);
		contentPane.add(separator_1);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioControl.limparAction();
			}
		});
		btnLimpar.setBounds(253, 233, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuarioControl.salvarAction();
			}
		});
		btnSalvar.setBounds(447, 233, 89, 23);
		contentPane.add(btnSalvar);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(627, 42, 120, 20);
		contentPane.add(tfSenha);
		usuarioControl = new UsuarioControl(tfNome, tfTel, tfEmail, tfCpf, tfLogin, tfSenha, tfRua, tfNumero, tfComplemento, tfBairro, tfCidade, tfEstado, tfCep, cbCargo, cbSuperior, usuario, usuarioDao);
				
	}
}