package com.projedata;

import java.math.BigDecimal;
import java.util.List;

public class AumentoSalario {
  public static void aumentaSalarioDeTodosFuncionariosPorPercentual(List<Funcionario> funcionarios,
      double percentualAumento) {
    for (int i = 0; i < funcionarios.size(); i++) {
      BigDecimal salarioOriginal = funcionarios.get(i).getSalario();
      BigDecimal aumento = salarioOriginal.multiply(new BigDecimal(percentualAumento))
          .divide(new BigDecimal("100"));
      BigDecimal novoSalario = salarioOriginal.add(aumento);
      funcionarios.get(i).setSalario(novoSalario);
    }
  }
}
