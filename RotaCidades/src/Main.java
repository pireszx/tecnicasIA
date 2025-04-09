import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> estadoFinal = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int tamanhoPopulacao = 10;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantidade de gerações: ");
        int quantidadeGeracoes = scanner.nextInt();

        System.out.print("Taxa de seleção [25 a 40]: ");
        int taxaSelecao = scanner.nextInt();
        int taxaReproducao = 100 - taxaSelecao;

        System.out.print("Taxa de mutação (gerações entre mutações): ");
        int taxaMutacao = scanner.nextInt();
        scanner.close();

        List<Cidade> populacao = new ArrayList<>();
        List<Cidade> novaPopulacao = new ArrayList<>();

        Cidade.gerarPopulacao(populacao, tamanhoPopulacao, estadoFinal);
        Collections.sort(populacao);
        Cidade.exibirPopulacao(populacao, 0);

        for (int i = 1; i <= quantidadeGeracoes; i++) {
            novaPopulacao.clear();

            Cidade.selecionarPorTorneio(populacao, novaPopulacao, taxaSelecao);
            Cidade.reproduzir(populacao, novaPopulacao, taxaReproducao, estadoFinal);

            if (i % taxaMutacao == 0) {
                Cidade.mutar(novaPopulacao, estadoFinal);
            }

            populacao.clear();

            populacao.addAll(novaPopulacao);

            Collections.sort(populacao);

            Cidade.exibirPopulacao(populacao, i);

            if (populacao.get(0).aptidao == 0) {
                System.out.println("\nRota encontrada");
                break;
            }
        }
    }
}