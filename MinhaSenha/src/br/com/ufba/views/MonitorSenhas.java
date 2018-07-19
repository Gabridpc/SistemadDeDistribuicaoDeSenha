package br.com.ufba.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.ufba.atendimento.Atendimento;
import br.com.ufba.atendimento.AtendimentoContext;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MonitorSenhas {

	private JFrame frame;
	private JTable table;
	private AtendimentoContext atendimentoCtx;
	private JLabel lblTextSenha;
	private JLabel lblSenha;
	private JLabel lblGuiche;
	private JLabel lblTextGuiche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitorSenhas window = new MonitorSenhas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonitorSenhas() {
		this.atendimentoCtx = new AtendimentoContext();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		createLabels();

		table = new JTable(createRowsTable());
		table.setBounds(209, 26, 223, 128);

		frame.getContentPane().add(table);
		frame.getContentPane().add(lblTextGuiche);
		frame.getContentPane().add(lblTextSenha);
		frame.getContentPane().add(lblSenha);
		frame.getContentPane().add(lblGuiche);

		Atendimento atendimento = this.atendimentoCtx.getAtendimentoAtual();

		if (atendimento == null) {
			this.lblSenha.setText("00000");
			this.lblGuiche.setText("00000");
		} else {
			this.lblSenha.setText(atendimento.toString());
			this.lblGuiche.setText(atendimento.getGuiche().toUpperCase());
		}
	}

	private void createLabels() {
		lblTextSenha = new JLabel("SENHA");
		lblTextSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextSenha.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTextSenha.setBounds(23, 26, 161, 43);

		lblSenha = new JLabel("New label");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblSenha.setBounds(23, 80, 161, 67);

		lblGuiche = new JLabel("New label");
		lblGuiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiche.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblGuiche.setBounds(23, 195, 161, 67);

		lblTextGuiche = new JLabel("GUICHE");
		lblTextGuiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextGuiche.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTextGuiche.setBounds(23, 158, 161, 33);
	}

	private DefaultTableModel createRowsTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Senha");
		model.addColumn("Guiche");

		for (Atendimento atendimento : this.atendimentoCtx.getTodosAtendimentosAtendidos(5)) {
			model.addRow(new Object[] { atendimento.toString(), atendimento.getGuiche().toUpperCase() });
		}

		return model;
	}
}
