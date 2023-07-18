package com.projedata.funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioController {
  public static List<Funcionario> cadastraFuncionarios() {
    List<Funcionario> funcionarios = new ArrayList<>();

    funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"));
    funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal(2284.38), "Operador"));
    funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal(9836.14), "Coordenador"));
    funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"));
    funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal(2234.68),
        "Recepcionista"));
    funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"));
    funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal(4071.84), "Contador"));
    funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal(3017.45), "Gerente"));
    funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal(1606.85),
        "Eletricista"));
    funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal(2799.93), "Gerente"));

    return funcionarios;
  }

  public static void removeFuncionarioPorNome(List<Funcionario> funcionarios, String nome) {
    int cont = 0;
    for (int i = 0; i < funcionarios.size(); i++) {
      if (funcionarios.get(i).getNome().equals(nome)) {
        funcionarios.remove(i);
        System.out.println("Funcionário " + nome + " removido.");
        cont++;
      }
    }
    if (cont == 0) {
      System.out.println("Nenhum funcionário com nome " + nome + " encontrado.");
    }
    System.out.println();
  }

  public static void imprimirTodosFuncionarios(List<Funcionario> funcionarios) {
    funcionarios.forEach(System.out::println);
    System.out.println();
  }

  public static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosAgrupadoPorFuncao) {
    funcionariosAgrupadoPorFuncao.forEach((funcao, funcionarios) -> {
      System.out.println("Funcao: " + funcao);
      funcionarios.forEach(System.out::println);
      System.out.println();
    });
  }

  public static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
    Map<String, List<Funcionario>> funcoes = funcionarios.stream()
        .collect(Collectors.groupingBy(Funcionario::getFuncao));
    return funcoes;
  }

  public static void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
    Collections.sort(funcionarios, new FuncionarioComparator());

    System.out.println("Lista de funcionários em ordem alfabética: ");
    for (Funcionario funcionario : funcionarios) {
      System.out.println(funcionario);
    }
    System.out.println();
  }

}
