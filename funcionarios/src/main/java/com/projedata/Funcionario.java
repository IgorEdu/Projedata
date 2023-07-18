package com.projedata;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
  private BigDecimal salario;
  private String funcao;
  DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  Locale brasil = new Locale("pt", "BR");
  NumberFormat formatoNumero = NumberFormat.getNumberInstance(brasil);

  public Funcionario() {
  }

  public Funcionario(String nome, LocalDate nascimento, BigDecimal salario, String funcao) {
    super.setNome(nome);
    super.setNascimento(nascimento);
    this.setSalario(salario);
    this.setFuncao(funcao);
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  @Override
  public String toString() {
    return "[Nome: " + super.getNome() + ", Data de Nascimento: " + super.getNascimento().format(formatoData)
        + ", Salário: " + formatoNumero.format(this.getSalario()) + ", Função: " + this.getFuncao() + "]";
  }

}
