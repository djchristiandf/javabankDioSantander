package services;

import java.util.List;

import entities.Cliente;
import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;
import entities.TipoConta;
import interfaces.IBanco;
import repositories.BancoRepository;

public class BancoService implements IBanco {

  private BancoRepository repository;

  public BancoService() {
    this.repository = new BancoRepository();
  }

  @Override
  public void abrirConta(Cliente cliente, TipoConta tipo) {
    Conta conta;
    if (tipo == TipoConta.CORRENTE) {
      conta = new ContaCorrente(cliente);
    } else {
      conta = new ContaPoupanca(cliente);
    }
    this.repository.adicionarConta(conta);
  }

  @Override
  public void encerrarConta(Conta conta) {
    this.repository.removerConta(conta);
  }

  @Override
  public void transferir(Conta contaOrigem, Conta contaDestino, double valor) {
    contaOrigem.transferir(valor, contaDestino);
  }

  @Override
  public List<Conta> listarContas() {
    return this.repository.listarContas();
  }

  @Override
  public double getSaldoTotal() {
    return this.repository.getSaldoTotal();
  }
}
