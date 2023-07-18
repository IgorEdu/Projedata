package com.projedata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.projedata.funcionario.Funcionario;
import com.projedata.funcionario.FuncionarioController;
import com.projedata.funcionario.salarios.SalarioController;
import com.projedata.pessoa.PessoaController;

public class Principal {
    public final static BigDecimal SALARIO_MINIMO = new BigDecimal(1212.00);

    public static void main(String[] args) {
        List<Funcionario> funcionarios = FuncionarioController.cadastraFuncionarios();

        FuncionarioController.removeFuncionarioPorNome(funcionarios, "João");

        FuncionarioController.imprimirTodosFuncionarios(funcionarios);

        SalarioController.aumentaSalarioDeTodosFuncionariosPorPercentual(funcionarios, 10);

        Map<String, List<Funcionario>> funcionariosAgrupadoPorFuncao = FuncionarioController
                .agruparFuncionariosPorFuncao(funcionarios);

        FuncionarioController.imprimirFuncionariosPorFuncao(funcionariosAgrupadoPorFuncao);

        PessoaController.imprimirAniversariantesPorMes(10, funcionarios);
        PessoaController.imprimirAniversariantesPorMes(12, funcionarios);

        PessoaController.imprimirFuncionariosComMaiorIdade(funcionarios);

        FuncionarioController.imprimirFuncionariosOrdemAlfabetica(funcionarios);

        SalarioController.imprimirTotalSalarios(funcionarios);

        SalarioController.imprimirCalculoPorSalarioMinimo(funcionarios, SALARIO_MINIMO);
    }

}
