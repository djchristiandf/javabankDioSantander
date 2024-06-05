package entities;

public class ContaPoupanca extends Conta{
  private static final double TAXA_RENDIMENTO = 0.5;

  public ContaPoupanca(Cliente cliente) {
    super(cliente, TipoConta.POUPANCA);
  }

  public void renderJuros() {
    this.depositar(this.getSaldo() * TAXA_RENDIMENTO);
  }

  @Override
  public void imprimirExtrato() {
    System.out.println("=== Extrato Conta Poupança ===");
    super.imprimirInfosComuns();
  }
}
