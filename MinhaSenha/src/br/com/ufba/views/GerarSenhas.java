package br.com.ufba.views;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.com.ufba.atendimento.Atendimento;
import br.com.ufba.atendimento.AtendimentoContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GerarSenhas implements ActionListener {

	private JFrame frame;
	private final AtendimentoContext atendimentoCtx;
	private JButton btnSenhaNormal;
	private JButton btnSenhaPreferencial;
	private JLabel lblTextSenha;
	private JLabel lblASuaSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarSenhas window = new GerarSenhas();
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
	public GerarSenhas() {
		initialize();
		this.atendimentoCtx = new AtendimentoContext();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createButtonGerarSenhaNormal();
		createButtonGerarSenhaPreferencial();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnSenhaNormal);
		frame.getContentPane().add(btnSenhaPreferencial);

		lblTextSenha = new JLabel("");
		lblTextSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextSenha.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		lblTextSenha.setBounds(231, 120, 201, 124);

		frame.getContentPane().add(lblTextSenha);

		JLabel lblNewLabel = new JLabel("Clique no bot\u00E3o para gerar sua senha");
		lblNewLabel.setBounds(44, 50, 276, 46);
		frame.getContentPane().add(lblNewLabel);

		lblASuaSenha = new JLabel("A sua senha \u00E9:");
		lblASuaSenha.setBounds(295, 142, 86, 14);
		frame.getContentPane().add(lblASuaSenha);
	}

	private void createButtonGerarSenhaNormal() {
		btnSenhaNormal = new JButton("Normal");
		btnSenhaNormal.setBounds(44, 120, 177, 60);
		btnSenhaNormal.setForeground(Color.WHITE);
		btnSenhaNormal.setBackground(new Color(0, 51, 255));
		btnSenhaNormal.addActionListener(this);
	}

	private void createButtonGerarSenhaPreferencial() {
		btnSenhaPreferencial = new JButton("Preferencial");
		btnSenhaPreferencial.setBounds(44, 191, 177, 53);
		btnSenhaPreferencial.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent buttonEvent) {
		if (buttonEvent.getActionCommand().equals("Normal")) {
			Atendimento atendimento = this.atendimentoCtx.gerarAtendimentoNormal();

			lblTextSenha.setText(atendimento.toString());
		} else if (buttonEvent.getActionCommand().equals("Preferencial")) {
			Atendimento atendimento = this.atendimentoCtx.gerarAtendimentoPreferencial();

			lblTextSenha.setText(atendimento.toString());
		}
	}
}
