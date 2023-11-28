import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Funcionario> funcionariosList = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormatter = new DecimalFormat("#,###.00");

        // Adição Funcionários
        funcionariosList
                .add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionariosList
                .add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        funcionariosList
                .add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionariosList
                .add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionariosList
                .add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionariosList
                .add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionariosList
                .add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionariosList
                .add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionariosList
                .add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionariosList
                .add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));

        // Remoção do funcionário "João"
        funcionariosList.removeIf(func -> func.getNome().equalsIgnoreCase("João"));

        // Impressão dos funcionários
        System.out.println("Funcionários");
        funcionariosList.forEach(func -> {
            System.out.println("Nome: " + func.getNome());
            System.out.println("Data de Nascimento: " + func.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + decimalFormatter.format(func.getSalario()));
            System.out.println("Função: " + func.getFuncao());
            System.out.println();
        });

        // Aumento de 10% no salário
        funcionariosList.forEach(func -> func.setSalario(func.getSalario().multiply(BigDecimal.valueOf(1.1))));

        // Funcionários mapeados e impressos por função
        Map<String, List<Funcionario>> funcionariosMap = funcionariosList.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("Funcionários por função: ");
        funcionariosMap.forEach((funcao, listaFunc) -> {
            System.out.println("Função: " + funcao);
            listaFunc.forEach(func -> {
                System.out.println("Nome: " + func.getNome());
                System.out.println("Data de Nascimento: " + func.getDataNascimento().format(dateFormatter));
                System.out.println("Salário: " + decimalFormatter.format(func.getSalario()));
                System.out.println();
            });
            System.out.println();
        });

        // Funcionários no mês 10 ou 12
        System.out.println("Funcionários nascidos entre os meses 10 ou 12: ");
        funcionariosList.stream()
                .filter(func -> (func.getDataNascimento().getMonthValue() == 10
                        || func.getDataNascimento().getMonthValue() == 12))
                .forEach(func -> {
                    System.out.println("Nome: " + func.getNome());
                    System.out.println("Data de Nascimento: " + func.getDataNascimento().format(dateFormatter));
                    System.out.println("Salário: " + decimalFormatter.format(func.getSalario()));
                    System.out.println("Função: " + func.getFuncao());
                    System.out.println();
                });

        // Funcionários mais velho
        Funcionario maisVelho = funcionariosList.get(0);
        for (Funcionario func : funcionariosList) {
            maisVelho = maisVelho.getDataNascimento().compareTo(func.getDataNascimento()) < 0 ? maisVelho : func;
        }

        System.out.println("Funcionário mais velho: ");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears());
        System.out.println();

        // Ordem Alfabética
        Collections.sort(funcionariosList, (f1, f2) -> f1.getNome().compareTo(f2.getNome()));

        System.out.println("Funcionários por ordem alfabética: ");
        System.out.println();
        for (Funcionario func : funcionariosList) {
            System.out.println("Nome: " + func.getNome());
            System.out.println("Data de Nascimento: " + func.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + decimalFormatter.format(func.getSalario()));
            System.out.println("Função: " + func.getFuncao());
            System.out.println();
        }

        // Soma salários
        BigDecimal somaSalarios = funcionariosList.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        System.out.println("Salário somado dos funcionários: " + somaSalarios);
        System.out.println();

        // Quantidade salários mínimos
        for (Funcionario func : funcionariosList) {
            BigDecimal salarioMin = func.getSalario().divide(BigDecimal.valueOf(1212), MathContext.DECIMAL32);
            System.out.println(func.getNome() + " ganha " + decimalFormatter.format(salarioMin) + " salários minímos");
        }
    }
}
