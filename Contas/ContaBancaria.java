package Contas;
import java.time.LocalDateTime;

public abstract class ContaBancaria implements Comparable<ContaBancaria> {
    private int numero;
    protected double saldo;
    private String CPF;
    private String nome;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataEncerramento;
    private boolean ativo;
    
    public ContaBancaria(int numero, double saldo, String cPF, String nome, LocalDateTime dataCadastro,
    LocalDateTime dataEncerramento, boolean ativo) {
        this.numero = numero;
        this.saldo = saldo;
        CPF = cPF;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.dataEncerramento = dataEncerramento;
        this.ativo = ativo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public abstract void configurarConta(String nome, String CPF);

    public abstract boolean sacar(double valor);
    public abstract boolean depositar(double valor);

    public int getNumeroConta(){
        return numero;
    }

    public abstract double getSaldo();
  

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public  void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public abstract void mostrarDados();

    public  boolean transferir(ContaBancaria conta, double valor){
        if(this.sacar(valor)){
            conta.depositar(valor);
            return true;
        }
        System.out.println("Nao foi possivel fazer a transferencia");
        return false;
    }

}