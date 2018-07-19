package br.com.ufba.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.ufba.atendimento.Atendimento;
import br.com.ufba.atendimento.AtendimentoContext;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class GerenciarSenhas implements ActionListener{

	private JFrame frame;
	private AtendimentoContext atendimentoCtx;
	private JTable table;
	private JLabel lblSenhasCadastradas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarSenhas window = new GerenciarSenhas();
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
	public GerenciarSenhas() {
		this.atendimentoCtx = new AtendimentoContext();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 723, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAtender = new JButton("Atender");
		btnAtender.setBounds(10, 24, 227, 52);
		btnAtender.addActionListener(this);
		
		frame.getContentPane().add(btnAtender);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 112, 655, 308);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(createRowsTable());
		scrollPane.setViewportView(table);
		
		lblSenhasCadastradas = new JLabel("Senhas cadastradas");
		lblSenhasCadastradas.setBounds(10, 87, 445, 14);
		frame.getContentPane().add(lblSenhasCadastradas);
	}
	
	private DefaultTableModel createRowsTable() {
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Senha"); 
		model.addColumn("Tipo");
		model.addColumn("Registrada em");
		model.addColumn("Atendida em");
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		for(Atendimento atendimento : this.atendimentoCtx.getTodosAtendimentos()) {
			String tipo = atendimento.getSenha().getTipo().equals("P") ? "Preferencial" : "Normal";
			String dataAtendimento = atendimento.getDataAtendimento() == null ? "Não atendida" : df.format(atendimento.getDataAtendimento());
			
			model.addRow(new Object[] {atendimento.getId(), tipo, df.format(atendimento.getDataRegistro()),  dataAtendimento});	
		}
		
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent buttonEvent) {
		if(buttonEvent.getActionCommand().equals("Atender")) {
			this.atendimentoCtx.atender();
			this.table.setModel(createRowsTable());
		}	
	}
}
