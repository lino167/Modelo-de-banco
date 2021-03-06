package Contas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Banco{
    private static Scanner ler;
    private String nomeBanco;
    private ArrayList <ContaBancaria> banco = new ArrayList<>();
    
    
    

    public Banco(String nomeBanco){
        this.nomeBanco = nomeBanco;
    }

    public String getNomeBanco(){
        return nomeBanco;
    }

    public ArrayList<ContaBancaria> getBanco() {
        return banco;
    }

    public ContaBancaria criarConta(String nome, String CPF) {
        ler = new Scanner(System.in);
        Random random;
        int numero, opcao;
        ContaBancaria auxiliar, conta;
        LocalDateTime dataAtual = LocalDateTime.now();

        if(!this.validarCliente(nome, CPF)){
            System.out.println("Cliente ja possui cadastro com outros dados");
            return null;
        }
        random = new Random();
        numero = random.nextInt(10000)+1;
        auxiliar = this.procurarConta(numero);
        while(auxiliar != null){
            numero = random.nextInt(10000)+1;
            auxiliar = this.procurarConta(numero);
        }

        do {
            System.out.println("0- Voltar ao Menu Principal");
            System.out.println("1- Conta Corrente");
            System.out.println("2- Conta Poupanca");
            System.out.println("3- Conta Salário");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a opção desejada: ");
            opcao = ler.nextInt();

            if(opcao == 1){
                conta = new ContaCorrente(numero, 0, CPF, nome, dataAtual, null, true);
                break;
            }else if(opcao == 2){
                conta = new ContaPoupanca(numero, 0, CPF, nome, dataAtual, null, true);
                break;
            }else{
                conta = new ContaSalario(numero, 0, CPF, nome, dataAtual, null, true);
                break;
            }
            

        }while(opcao != 1 && opcao != 2);

        this.inserir(conta);
        return conta;
    }

   
    public boolean validarCliente(String nome, String CPF){
        for(ContaBancaria conta : banco){
            if(conta.getCPF().equals(CPF)) {
                if(!conta.getNome().equals(nome)){
                    return false;
                }
                return true;
            }
        }
        return true;
    }
    public boolean inserir(ContaBancaria conta){
        if(!this.getBanco().contains(conta)){
            this.getBanco().add(conta);
            System.out.println("Conta cadastrada com sucesso!");
            System.out.println();
            return true;
        }
        System.out.println("Conta ja cadastrada");
        System.out.println();
        return false;
    }


    public ContaBancaria procurarConta(int numero){
        for(ContaBancaria conta : banco){
            if(conta.getNumeroConta() == numero){
                return conta;
            }
        }
        return null;
    }

    


    public void mostrarDados(){
        System.out.println(" -----------------------------------");
        System.out.println("| ***Clientes do Banco " + this.getNomeBanco() + "*** |  ");
        System.out.println(" -----------------------------------");
        for(ContaBancaria x : banco){
            x.mostrarDados();
          
            System.out.println();
        }
     
    }

    


    }
