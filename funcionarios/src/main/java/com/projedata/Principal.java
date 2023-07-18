package com.projedata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public final static BigDecimal SALARIO_MINIMO = new BigDecimal(1212.00);

    public static void main(String[] args) {
        List<Funcionario> funcionarios = cadastraFuncionarios();

        removeFuncionarioPorNome(funcionarios, "João");

        imprimirTodosFuncionarios(funcionarios);

        aumentaSalarioDeTodosFuncionariosPorPercentual(funcionarios, 10);

        Map<String, List<Funcionario>> funcionariosAgrupadoPorFuncao = agruparFuncionariosPorFuncao(funcionarios);

        imprimirFuncionariosPorFuncao(funcionariosAgrupadoPorFuncao);

        imprimirAniversariantesPorMes(10, funcionarios);
        imprimirAniversariantesPorMes(12, funcionarios);

        imprimirFuncionariosComMaiorIdade(funcionarios);

        imprimirFuncionariosOrdemAlfabetica(funcionarios);

        imprimirTotalSalarios(funcionarios);

        imprimirCalculoPorSalarioMinimo(funcionarios);
    }

    private static void imprimirCalculoPorSalarioMinimo(List<Funcionario> funcionarios) {
        System.out.println("Cálculo de salários mínimos por funcionário:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal calculado = funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println("[Nome: " + funcionario.getNome() + ", Salários mínimos: " + calculado + "]");
        }
        System.out.println();
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        double totalSalarios = 0;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios += funcionario.getSalario().doubleValue();
        }

        System.out.println("Soma dos salários de todos os funcionários é: " + totalSalarios);
        System.out.println();
    }

    private static void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        Collections.sort(funcionarios, new ComparadorFuncionario());

        System.out.println("Lista de funcionários em ordem alfabética: ");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
        System.out.println();
    }

    private static void imprimirFuncionariosComMaiorIdade(List<Funcionario> funcionarios) {
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

    private static void imprimirAniversariantesPorMes(int mes, List<Funcionario> funcionarios) {
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

    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosAgrupadoPorFuncao) {
        funcionariosAgrupadoPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("Funcao: " + funcao);
            funcionarios.forEach(System.out::println);
            System.out.println();
        });
    }

    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcoes = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        return funcoes;
    }

    private static void aumentaSalarioDeTodosFuncionariosPorPercentual(List<Funcionario> funcionarios,
            double percentualAumento) {
        for (int i = 0; i < funcionarios.size(); i++) {
            BigDecimal salarioOriginal = funcionarios.get(i).getSalario();
            BigDecimal aumento = salarioOriginal.multiply(new BigDecimal(percentualAumento))
                    .divide(new BigDecimal("100"));
            BigDecimal novoSalario = salarioOriginal.add(aumento);
            funcionarios.get(i).setSalario(novoSalario);
        }
    }

    private static void imprimirTodosFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
        System.out.println();
    }

    private static void removeFuncionarioPorNome(List<Funcionario> funcionarios, String nome) {
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

}
