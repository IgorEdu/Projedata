package com.projedata.funcionario.salarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.projedata.funcionario.Funcionario;

public class SalarioController {
  public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
    double totalSalarios = 0;
    for (Funcionario funcionario : funcionarios) {
      totalSalarios += funcionario.getSalario().doubleValue();
    }
    System.out.println("Soma dos salários de todos os funcionários é: " + totalSalarios);
    System.out.println();
  }

  public static void imprimirCalculoPorSalarioMinimo(List<Funcionario> funcionarios, BigDecimal SALARIO_MINIMO) {
    System.out.println("Cálculo de salários mínimos por funcionário:");
    for (Funcionario funcionario : funcionarios) {
      BigDecimal calculado = funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
      System.out.println("[Nome: " + funcionario.getNome() + ", Salários mínimos: " + calculado + "]");
    }
    System.out.println();
  }

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
