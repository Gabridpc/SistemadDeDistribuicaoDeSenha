package br.com.ufba.atendimento;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import br.com.ufba.utils.FileContext;

public class AtendimentoContext {

	private Queue<Atendimento> atendimentos;
	private final int TEMPO_DE_ESPERA = 5000;
	private int ultimoId;
	private FileContext fileContext;

	@SuppressWarnings("unchecked")
	public AtendimentoContext() {
		fileContext = new FileContext("dados");

		try {
			this.atendimentos = (LinkedList<Atendimento>) fileContext.readFile();

			if (this.atendimentos == null) {
				this.atendimentos = new LinkedList<>();
			} else {
				Atendimento atendimento = this.atendimentos.stream()
						.sorted(Comparator.comparing(Atendimento::getDataRegistro))
						.collect(Collectors.toCollection(LinkedList::new)).getLast();

				this.ultimoId = atendimento.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Atendimento atender() {
		Calendar calendar = Calendar.getInstance();

		Queue<Atendimento> atendimentosPreferenciais = this.atendimentos.stream()
				.filter(s -> s.getDataAtendimento() == null && s.getSenha().getTipo().equals("P"))
				.sorted(Comparator.comparing(Atendimento::getDataRegistro))
				.collect(Collectors.toCollection(LinkedList::new));

		Queue<Atendimento> atendimentosNormais = this.atendimentos.stream()
				.filter(s -> s.getDataAtendimento() == null && s.getSenha().getTipo().equals("N"))
				.sorted(Comparator.comparing(Atendimento::getDataRegistro))
				.collect(Collectors.toCollection(LinkedList::new));

		for (Atendimento atendimento : atendimentosPreferenciais) {
			calendar.setTime(atendimento.getDataRegistro());

			if (calendar.getTimeInMillis() >= this.TEMPO_DE_ESPERA) {
				atendimento.setDataAtendimento(new Date());

				try {
					fileContext.saveFile(this.atendimentos);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return atendimento;
			}
		}

		Atendimento atendimentoNormal = atendimentosNormais.peek();
		atendimentoNormal.setDataAtendimento(new Date());

		try {
			fileContext.saveFile(this.atendimentos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return atendimentoNormal;
	}

	public Atendimento gerarAtendimentoNormal() {
		this.ultimoId++;

		Senha novaSenha = new Senha("N");

		Atendimento atendimento = new Atendimento(this.ultimoId, novaSenha);
		atendimentos.add(atendimento);

		try {
			fileContext.saveFile(this.atendimentos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return atendimento;
	}

	public Atendimento gerarAtendimentoPreferencial() {
		this.ultimoId++;

		Senha novaSenha = new Senha("P");

		Atendimento atendimento = new Atendimento(this.ultimoId, novaSenha);
		atendimentos.add(atendimento);

		return atendimento;
	}

	public Atendimento getAtendimentoAtual() {
		return this.atendimentos.stream()
				.sorted(Comparator.comparing(Atendimento::getDataRegistro)
						.thenComparing(Atendimento::getDataAtendimento).reversed())
				.filter(s -> s.getDataAtendimento() != null).findFirst().orElse(null);
	}

	public Queue<Atendimento> getTodosAtendimentos() {
		return this.atendimentos;
	}

	public Queue<Atendimento> getTodosAtendimentosNaoAtendidos() {
		return this.atendimentos.stream().filter(s -> s.getDataAtendimento() == null)
				.sorted(Comparator.comparing(Atendimento::getDataRegistro))
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Queue<Atendimento> getTodosAtendimentosAtendidos(int count) {
		return this.atendimentos.stream().filter(s -> s.getDataAtendimento() != null)
				.sorted(Comparator.comparing(Atendimento::getDataRegistro)
						.thenComparing(Atendimento::getDataAtendimento).reversed())
				.limit(count).collect(Collectors.toCollection(LinkedList::new));
	}
}
