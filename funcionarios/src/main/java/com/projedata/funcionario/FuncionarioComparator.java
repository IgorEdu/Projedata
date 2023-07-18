package com.projedata.funcionario;

import java.util.Comparator;

public class FuncionarioComparator implements Comparator<Funcionario> {
  @Override
  public int compare(Funcionario funcionario1, Funcionario funcionario2) {
    return funcionario1.getNome().compareTo(funcionario2.getNome());
  }
}
