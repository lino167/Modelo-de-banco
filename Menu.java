import java.io.FileNotFoundException;
import java.util.Scanner;

import Contas.Banco;
import Contas.ContaBancaria;

public class Menu {

    private static Scanner scanner;
    private static Banco banco;
    
    public static void main(String[] args) throws FileNotFoundException {
        
        scanner = new Scanner(System.in);
        banco = new Banco("ZATHA");
        int opcao;

        do{
            System.out.println(" ------------------ ");
            System.out.println("|------ MENU ------|");
            System.out.println(" ------------------ ");
            
            System.out.println("1- Criar Conta");
            System.out.println("2- Selecionar conta");
            System.out.println("3- Gerar Relatorio");
            System.out.println("0- Finalizar");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            
            switch (opcao){
                case 1:
                    criarConta();
                    break;
                case 2:
                selecionarConta();
                    break;
                case 3:
                gerarRelatorio();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcão inválida");
                    break;
            }

        }while(opcao != 0);


    }
    private static void criarConta() throws FileNotFoundException {
        String nome, CPF;
        int opcao = -1;
        ContaBancaria conta;

        while(opcao != 0 && opcao != 1 && opcao != 2){
            System.out.println("0- Voltar ao Menu Anterior");
            System.out.println("1- Fazer o Cadastro");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    scanner.nextLine();
                    System.out.print("Insira o nome: ");
                    nome = scanner.nextLine();
                    do {
                        System.out.print("Insira seu CPF(ex: 100.000.000-00): ");
                        CPF = scanner.nextLine();
                    }while(CPF.length() != 14 || CPF.charAt(3) != '.' || CPF.charAt(7) != '.' || CPF.charAt(11) != '-');

                    nome = nome.toUpperCase();
                    conta = banco.criarConta(nome, CPF);
                    if(conta != null){
                        System.out.println("Número da sua conta: " + conta.getNumeroConta());
                        System.out.println("----------------------------------------------");
                        break;
                    }
                    break;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }

        }
    }
    private static void selecionarConta(){
        int numeroConta, opcao;
        double valor;
        System.out.print("Insira o numero da conta: ");
        numeroConta = scanner.nextInt();
        ContaBancaria conta = banco.procurarConta(numeroConta);

        if(conta != null){
            do {
                System.out.println("0- Voltar ao Menu Anterior");
                System.out.println("1- Depositar");
                System.out.println("2- Sacar");
                System.out.println("3- Transferir");
                System.out.println("4- Gerar Relatório");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();

                switch (opcao){
                    case 1:
                        System.out.print("Insira o valor do depósito: ");
                        valor = scanner.nextDouble();
                        conta.depositar(valor);
                        break;
                    case 2:
                        System.out.print("Insira o valor do saque:");
                        valor = scanner.nextDouble();
                        conta.sacar(valor);
                        break;
                    case 3:
                        System.out.print("Número da Conta para tranferência: ");
                        numeroConta = scanner.nextInt();
                        ContaBancaria tmp = banco.procurarConta(numeroConta);
                        if(tmp != null){
                            System.out.print("Insira o valor a ser transfêrido: ");
                            valor = scanner.nextDouble();
                            if(conta.transferir(tmp, valor)){
                                System.out.println("Transferência feita com sucesso!");
                                break;
                            }
                            System.out.println("Valor do saque indisponível!");
                            break;
                        }
                        System.out.println("Conta inexistente!");
                        break;
                    case 4:
                        conta.mostrarDados();
                }

            }while(opcao != 0);

        }else{
            System.out.println("Conta Inexistente");
        }
    }
    private  static void gerarRelatorio(){
        banco.mostrarDados();
    }
    }
    

