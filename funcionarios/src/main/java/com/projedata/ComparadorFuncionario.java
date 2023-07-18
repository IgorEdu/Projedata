package com.projedata;

import java.util.Comparator;

public class ComparadorFuncionario implements Comparator<Funcionario> {
  @Override
  public int compare(Funcionario funcionario1, Funcionario funcionario2) {
    return funcionario1.getNome().compareTo(funcionario2.getNome());
  }
}
