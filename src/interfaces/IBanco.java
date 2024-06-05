package interfaces;

import java.util.List;

import entities.Cliente;
import entities.Conta;
import entities.TipoConta;

public interface IBanco {
  void abrirConta(Cliente cliente, TipoConta tipo);

  void encerrarConta(Conta conta);

  void transferir(Conta contaOrigem, Conta contaDestino, double valor);

  List<Conta> listarContas();

  double getSaldoTotal();
}
