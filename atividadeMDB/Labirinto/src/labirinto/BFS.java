/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labirinto;

/**
 *
 * @author pedro
 */
import java.util.*;

public class BFS implements Busca {
    @Override
    public void buscar(int[][] labirinto, int[] entrada, int[] saida) {
        int N = labirinto.length;
        boolean[][] visitado = new boolean[N][N];
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> fila = new LinkedList<>();
        fila.add(new int[]{entrada[0], entrada[1], 0});
        visitado[entrada[0]][entrada[1]] = true;

        while (!fila.isEmpty()) {
            int[] atual = fila.poll();
            int x = atual[0], y = atual[1], passos = atual[2];

            if (x == saida[0] && y == saida[1]) {
                System.out.println("BFS encontrou a saída em " + passos + " passos.");
                return;
            }

            for (int[] d : direcoes) {
                int nx = x + d[0], ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visitado[nx][ny] && labirinto[nx][ny] != -1) {
                    visitado[nx][ny] = true;
                    fila.add(new int[]{nx, ny, passos + 1});
                }
            }
        }
        System.out.println("BFS não encontrou um caminho.");
    }
}

