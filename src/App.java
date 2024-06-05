import entities.Cliente;
import entities.Conta;
import entities.ContaPoupanca;
import entities.TipoConta;
import services.BancoService;

public class App {
    public static void main(String[] args) throws Exception {
        BancoService bancoService = new BancoService();

        Cliente cliente1 = new Cliente();
        cliente1.setNome("João");
        cliente1.setCpf("123.456.789-00");
        cliente1.setEndereco("Rua X, 123");
        cliente1.setTelefone("(11) 98765-4321");

        bancoService.abrirConta(cliente1, TipoConta.CORRENTE);
        Conta cc = bancoService.listarContas().get(0);
        cc.depositar(100.0);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria");
        cliente2.setCpf("987.654.321-00");
        cliente2.setEndereco("Avenida Y, 456");
        cliente2.setTelefone("(11) 87654-3210");

        bancoService.abrirConta(cliente2, TipoConta.POUPANCA);
        Conta cp = bancoService.listarContas().get(1);
        cp.depositar(50.0);

        bancoService.transferir(cc, cp, 20.0);

        System.out.println("Informações da Conta Corrente:");
        System.out.println("Nome: " + cc.getCliente().getNome());
        System.out.println("CPF: " + cc.getCliente().getCpf());
        System.out.println("Endereço: " + cc.getCliente().getEndereco());
        System.out.println("Telefone: " + cc.getCliente().getTelefone());
        cc.imprimirExtrato();

        System.out.println("\nInformações da Conta Poupança:");
        System.out.println("Nome: " + cp.getCliente().getNome());
        System.out.println("CPF: " + cp.getCliente().getCpf());
        System.out.println("Endereço: " + cp.getCliente().getEndereco());
        System.out.println("Telefone: " + cp.getCliente().getTelefone());
        cp.imprimirExtrato();

        System.out.println("\nSaldo total do banco: R$ " + bancoService.getSaldoTotal());

        // Continuar sacando da conta corrente até ficar sem saldo
        while (cc.getSaldo() > 0) {
            try {
                cc.sacar(10.0);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        cc.imprimirExtrato();

        // Adicionar juros na conta poupança
        if (cp instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) cp;
            contaPoupanca.renderJuros();
            cp.imprimirExtrato();
        }

        System.out.println("\nSaldo total do banco: R$ " + bancoService.getSaldoTotal());

        // Encerrar a conta corrente
        bancoService.encerrarConta(cc);

        // Verificar a lista de contas após o encerramento
        System.out.println("\nContas após o encerramento:");
        for (Conta conta : bancoService.listarContas()) {
            System.out.println("Nome: " + conta.getCliente().getNome());
            System.out.println("CPF: " + conta.getCliente().getCpf());
            System.out.println("Endereço: " + conta.getCliente().getEndereco());
            System.out.println("Telefone: " + conta.getCliente().getTelefone());
            conta.imprimirExtrato();
            System.out.println();
        }

        System.out.println("\nSaldo total do banco: R$ " + bancoService.getSaldoTotal());
    }
}
