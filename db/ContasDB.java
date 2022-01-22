package db;

import java.util.ArrayList;

import Contas.ContaBancaria;

public class ContasDB {
    private ArrayList <ContaBancaria> banco = new ArrayList<>();

    public ArrayList<ContaBancaria> getBanco() {
        return banco;
    }
public void addNovaConta(ContaBancaria contaBancaria){
    banco.add(contaBancaria);
}
    
}
