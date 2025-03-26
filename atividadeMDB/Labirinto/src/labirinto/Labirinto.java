/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labirinto;

import java.util.Random;

public class Labirinto {
    private int[][] grid;
    private int N;
    private int[] entrada1, entrada2, saida;

    public Labirinto(int N, int M) {
        this.N = N;
        this.grid = new int[N][N];
        gerarObstaculos(M);
        gerarEntradasESaida();
    }

    private void gerarObstaculos(int M) {
        Random rand = new Random();
        int count = 0;
        while (count < M) {
            int x = rand.nextInt(N);
            int y = rand.nextInt(N);
            if (grid[x][y] == 0) {
                grid[x][y] = -1; // Obstáculo
                count++;
            }
        }
    }

    private void gerarEntradasESaida() {
        Random rand = new Random();
        
        // Gerar saída
        int sx, sy;
        do {
            sx = rand.nextInt(N);
            sy = rand.nextInt(N);
        } while (grid[sx][sy] == -1);
        grid[sx][sy] = 2;
        saida = new int[]{sx, sy};

        // Gerar entrada 1
        int ex1, ey1;
        do {
            ex1 = rand.nextInt(N);
            ey1 = rand.nextInt(N);
        } while (grid[ex1][ey1] != 0);
        grid[ex1][ey1] = 1;
        entrada1 = new int[]{ex1, ey1};

        // Gerar entrada 2
        int ex2, ey2;
        do {
            ex2 = rand.nextInt(N);
            ey2 = rand.nextInt(N);
        } while ((grid[ex2][ey2] != 0) || (ex2 == ex1 && ey2 == ey1));
        grid[ex2][ey2] = 1;
        entrada2 = new int[]{ex2, ey2};
    }

    public int[][] getGrid() {
        return grid;
    }

    public int[] getEntrada1() {
        return entrada1;
    }

    public int[] getEntrada2() {
        return entrada2;
    }

    public int[] getSaida() {
        return saida;
    }

    public void imprimirLabirinto() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((grid[i][j] == -1 ? "X" : grid[i][j]) + " ");
            }
            System.out.println();
        }
    }
}

