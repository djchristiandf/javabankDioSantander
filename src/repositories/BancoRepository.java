package repositories;

import java.util.ArrayList;
import java.util.List;

import entities.Conta;

public class BancoRepository {
  private List<Conta> contas;

  public BancoRepository() {
    this.contas = new ArrayList<>();
  }

  public void adicionarConta(Conta conta) {
    this.contas.add(conta);
  }

  public void removerConta(Conta conta) {
    this.contas.remove(conta);
  }

  public List<Conta> listarContas() {
    return new ArrayList<>(this.contas);
  }

  public double getSaldoTotal() {
    return this.contas.stream()
        .mapToDouble(Conta::getSaldo)
        .sum();
  }
}
