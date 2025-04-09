import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Cidade implements Comparable<Cidade> {
    public List<Integer> rota;
    public int aptidao;
    private static final Random random = new Random();

    public Cidade(List<Integer> rota, List<Integer> estadoFinal) {
        this.rota = new ArrayList<>(rota);
        this.aptidao = calcularAptidao(estadoFinal);
    }

    public static void gerarPopulacao(List<Cidade> populacao, int tamanhoPopulacao, List<Integer> estadoFinal) {
        for (int i = 0; i < tamanhoPopulacao; i++) {
            List<Integer> rotaGerada = new ArrayList<>(estadoFinal);
            Collections.shuffle(rotaGerada);
            populacao.add(new Cidade(rotaGerada, estadoFinal));
        }
    }

    private int calcularAptidao(List<Integer> estadoFinal) {
        int nota = 0;

        for (int i = 0; i < this.rota.size(); i++) {
            if (i < estadoFinal.size() && !this.rota.get(i).equals(estadoFinal.get(i))) {
                nota += 10;
            }
        }

        for (int i = 0; i < this.rota.size() - 1; i++) {
            if (this.rota.get(i) > this.rota.get(i+1)) {
                nota += 10;
            }
        }

        Set<Integer> cidadesUnicas = new HashSet<>(this.rota);
        nota += 10 * (this.rota.size() - cidadesUnicas.size());

        return nota;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int num : this.rota) {
            sb.append("Cidade_").append(num).append("  / ");
        }
        return sb.toString() + "- Nota de Aptidão: " + this.aptidao;
    }

    public static void exibirPopulacao(List<Cidade> populacao, int numeroGeracao) {
        System.out.println("\nGeração " + numeroGeracao + ":");
        for (int i = 0; i < populacao.size(); i++) {
            System.out.println((i+1) + ". " + populacao.get(i));
        }
    }

    public static void selecionarPorTorneio(List<Cidade> populacao, List<Cidade> novaPopulacao, int taxaSelecao) {
        int qtdSelecionados = taxaSelecao * populacao.size() / 100;
        novaPopulacao.add(Collections.min(populacao));

        while (novaPopulacao.size() <= qtdSelecionados) {
            List<Cidade> torneio = new ArrayList<>();
            while (torneio.size() < 3) {
                Cidade candidato = populacao.get(random.nextInt(populacao.size()));
                if (!torneio.contains(candidato)) {
                    torneio.add(candidato);
                }
            }
            novaPopulacao.add(Collections.min(torneio));
        }
    }

    public static void reproduzir(List<Cidade> populacao, List<Cidade> novaPopulacao, int taxaReproducao, List<Integer> estadoFinal) {
        int qtdReproduzidos = taxaReproducao * populacao.size() / 100;

        for (int i = 0; i < qtdReproduzidos; i += 2) {
            Cidade pai = populacao.get(random.nextInt(populacao.size()));
            Cidade mae = populacao.get(random.nextInt(populacao.size()));

            int ponto = random.nextInt(estadoFinal.size() - 1) + 1;

            List<Integer> filho1 = new ArrayList<>(pai.rota.subList(0, ponto));
            filho1.addAll(mae.rota.subList(ponto, mae.rota.size()));

            List<Integer> filho2 = new ArrayList<>(mae.rota.subList(0, ponto));
            filho2.addAll(pai.rota.subList(ponto, pai.rota.size()));

            novaPopulacao.add(new Cidade(filho1, estadoFinal));
            novaPopulacao.add(new Cidade(filho2, estadoFinal));
        }

        while (novaPopulacao.size() > populacao.size()) {
            novaPopulacao.remove(novaPopulacao.size() - 1);
        }
    }

    public static void mutar(List<Cidade> populacao, List<Integer> estadoFinal) {
        int qtdMutantes = random.nextInt(populacao.size() / 5) + 1;

        for (int i = 0; i < qtdMutantes; i++) {
            int pos = random.nextInt(populacao.size());
            List<Integer> novaRota = new ArrayList<>(populacao.get(pos).rota);

            int idx1 = random.nextInt(novaRota.size());
            int idx2 = random.nextInt(novaRota.size());
            Collections.swap(novaRota, idx1, idx2);

            populacao.set(pos, new Cidade(novaRota, estadoFinal));
        }
    }

    @Override
    public int compareTo(Cidade outra) {
        return Integer.compare(this.aptidao, outra.aptidao);
    }
}