package com.projedata.pessoa;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.projedata.funcionario.Funcionario;

public class PessoaController {
  public static void imprimirFuncionariosComMaiorIdade(List<Funcionario> funcionarios) {
    Funcionario funcionarioMaisVelho = null;

    for (Funcionario funcionario : funcionarios) {
      if (funcionarioMaisVelho == null
          || funcionario.getNascimento().isBefore(funcionarioMaisVelho.getNascimento())) {
        funcionarioMaisVelho = funcionario;
      }
    }

    if (funcionarioMaisVelho != null) {
      LocalDate dataAtual = LocalDate.now();
      Period periodo = Period.between(funcionarioMaisVelho.getNascimento(), dataAtual);
      int idade = periodo.getYears();

      System.out.println("O funcionário com a maior idade é " + funcionarioMaisVelho.getNome() + " com "
          + idade + " anos.");
    } else {
      System.out.println("A lista está vazia.");
    }
    System.out.println();
  }

  public static void imprimirAniversariantesPorMes(int mes, List<Funcionario> funcionarios) {
    DateFormatSymbols dfs = DateFormatSymbols.getInstance(new Locale("pt", "BR"));
    String nomeMes = dfs.getMonths()[Month.of(mes).getValue() - 1];

    List<Funcionario> aniversariantes = new ArrayList<>();
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getNascimento().getMonthValue() == mes) {
        aniversariantes.add(funcionario);
      }
    }

    System.out.println("Aniversariantes do mês de " + nomeMes + ":");
    if (aniversariantes.size() > 0) {
      for (Funcionario funcionario : aniversariantes) {
        System.out.println(funcionario);
      }
    } else {
      System.out.println("Não possuí aniversariantes no mes de " + nomeMes);
    }

    System.out.println();
  }
}
