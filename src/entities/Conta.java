package entities;

public abstract class Conta {
  private static final int AGENCIA_PADRAO = 1;
  private static int SEQUENCIAL = 1;

  protected int agencia;
  protected int numero;
  protected double saldo;
  protected Cliente cliente;
  private TipoConta tipo;

  public Conta(Cliente cliente, TipoConta tipo) {
    this.agencia = Conta.AGENCIA_PADRAO;
    this.numero = SEQUENCIAL++;
    this.cliente = cliente;
    this.tipo = tipo;
  }

  public void sacar(double valor) {
    if (saldo >= valor) {
      saldo -= valor;
    } else {
      throw new IllegalArgumentException("Saldo insuficiente.");
    }
  }

  public void depositar(double valor) {
    saldo += valor;
  }

  public void transferir(double valor, Conta contaDestino) {
    this.sacar(valor);
    contaDestino.depositar(valor);
  }

  public int getAgencia() {
    return agencia;
  }

  public int getNumero() {
    return numero;
  }

  public double getSaldo() {
    return saldo;
  }

  public TipoConta getTipo() {
    return tipo;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public abstract void imprimirExtrato();

  protected void imprimirInfosComuns() {
    System.out.println(String.format("Titular: %s", this.cliente.getNome()));
    System.out.println(String.format("Agencia: %d", this.agencia));
    System.out.println(String.format("Numero: %d", this.numero));
    System.out.println(String.format("Saldo: %.2f", this.saldo));
    System.out.println(String.format("Tipo de Conta: %s", this.tipo.name()));
  }
}
