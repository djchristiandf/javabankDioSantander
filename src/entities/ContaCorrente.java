package entities;

public class ContaCorrente extends Conta{
  private static final double TAXA_SAQUE = 2.0;

    public ContaCorrente(Cliente cliente) {
        super(cliente, TipoConta.CORRENTE);
    }

    @Override
    public void sacar(double valor) {
        valor += TAXA_SAQUE;
        super.sacar(valor);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
    }
}
