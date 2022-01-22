package Contas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContaSalario extends ContaBancaria implements Tributavel{

    private int diaAnversario;
    private double taxaDeJuros = 0.05;
    private double limite = 500.00;
    
     ContaSalario(int numero, double saldo, String cPF, String nome, LocalDateTime dataCadastro,
            LocalDateTime dataEncerramento, boolean ativo) {
        super(numero, saldo, cPF, nome, dataCadastro, dataEncerramento, ativo);
 
    }
    
    public double getSaldo() {
        return this.saldo + this.taxaDeJuros*this.saldo;
    }

    @Override
    public String toString() {
        return "ContaSalario [diaAnversario=" 
        + diaAnversario 
        + ", taxaDeJuros=" 
        + taxaDeJuros + "]";
    }

    private double getLimite() {
        return this.limite;
    }

    @Override
    public void configurarConta(String nome, String CPF) {
        this.setDataCadastro(LocalDateTime.now());
        this.setCPF(CPF);
        this.setNome(nome);
        this.setAtivo(true);
        this.setDataEncerramento(null);
        
    }

    @Override
    public boolean sacar(double valor) {
        if(this.getSaldo() - valor >= this.getLimite() * -1){
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
        System.out.println("Saque inválido, ultrapassou o limite");
        System.out.println();
        return false;
    }

    @Override
    public boolean depositar(double valor) {
        double deposito = valor;
        this.setSaldo(this.getSaldo() + deposito * this.getValorImposto());
        return true;
    }
    
    @Override
    public void mostrarDados() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        System.out.println("***Conta Salário***");
        System.out.println("Cliente: " + this.getNome());
        System.out.println("CPF: " + this.getCPF());
        System.out.println("Numero da Conta: " + this.getNumeroConta());
        System.out.println("Data de Cadastro: " + dtf.format(this.getDataCadastro()));
        System.out.println("Saldo: R$" + this.getSaldo());
        System.out.println("Limite: R$" + this.getLimite());
        System.out.println();
        
}

    @Override
    public int compareTo(ContaBancaria o) {
        return this.getNome().compareTo(o.getNome());
    }
    
    public double getValorImposto() {
        return this.getSaldo()*0.01;
    }
}

