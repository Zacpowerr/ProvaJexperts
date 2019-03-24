package com.eduardo.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import com.eduardo.control.UsuarioControl;
import com.eduardo.model.Usuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Listagem extends JFrame {

	private JTable table;
	private JComboBox comboBox;
	private UsuarioControl usuarioControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listagem frame = new Listagem();
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
	public Listagem() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				usuarioControl.listarAction();
			}
		});

		setBounds(100, 100, 749, 339);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 713, 250);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Usu\u00E1rio", "Cargo", "Telefone",
				"Email", "Cpf", "Login", "Senha", "Rua", "N\u00FAmero", "Complemento", "CEP" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 22, 37, 14);
		getContentPane().add(lblFiltro);

		comboBox = new JComboBox();
		comboBox.setBounds(68, 19, 170, 20);
		getContentPane().add(comboBox);

		JButton btnLimparfiltro = new JButton("Limpar filtro");
		btnLimparfiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioControl.listarAction();
			}
		});
		btnLimparfiltro.setBounds(586, 18, 125, 23);
		getContentPane().add(btnLimparfiltro);
		usuarioControl = new UsuarioControl(table, comboBox);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario u = (Usuario) comboBox.getSelectedItem();
				usuarioControl.listarAction(u);

			}
		});
		btnFiltrar.setBounds(287, 18, 89, 23);
		getContentPane().add(btnFiltrar);
		usuarioControl.popularCBPesquisa();
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
