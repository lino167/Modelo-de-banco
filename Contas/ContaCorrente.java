package Contas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContaCorrente extends ContaBancaria implements Tributavel {

    private double taxaDeOperacao = 5.00;
    private double chequeEspecial = 500.00;
    public double getValorImposto() {
        return this.getSaldo()*0.01;
    }
   
    
    public ContaCorrente(int numero, double saldo, String cPF, String nome, LocalDateTime dataCadastro,
            LocalDateTime dataEncerramento, boolean ativo) {
        super(numero, saldo, cPF, nome, dataCadastro, dataEncerramento, ativo);
       
        
    }



    public double getTaxaDeOperacao() {
        return taxaDeOperacao;
    }


    public double getChequeEspecial() {
        return chequeEspecial;
    }
    @Override
    public void configurarConta(String nome, String CPF) {
        this.setDataCadastro(LocalDateTime.now());
        this.setCPF(CPF);
        this.setNome(nome);
        this.setAtivo(true);
        this.setDataEncerramento(null);
        return;
    }
    
    @Override
    public boolean sacar(double valor) {
        double disponivel = this.chequeEspecial + this.getSaldo();
        if(valor > disponivel) {
            System.out.println("Voçê não possui saldo disponível");
            return false;
        }else{
            this.setSaldo(this.getSaldo() - valor - taxaDeOperacao);
            return true;
        }
    }

    @Override
    public boolean depositar(double valor){
        double deposito = valor;
        this.setSaldo(this.getSaldo() + deposito - taxaDeOperacao);
        return true;
    }

    @Override
    public void mostrarDados(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        System.out.println("***Conta Corrente***");
        System.out.println("Cliente: " + this.getNome());
        System.out.println("CPF: " + this.getCPF());
        System.out.println("Numero da Conta: " + this.getNumeroConta());
        System.out.println("Data de Cadastro: " + dtf.format(this.getDataCadastro()));
        System.out.println("Saldo: R$" + this.getSaldo());
        System.out.println("Taxa de Operacao: R$" + this.getTaxaDeOperacao());
        System.out.println();
    }


    @Override
    public int compareTo(ContaBancaria o) {
        return this.getNome().compareTo(o.getNome());
    }


    @Override
    public double getSaldo() {
        return getSaldo();
    }
}
