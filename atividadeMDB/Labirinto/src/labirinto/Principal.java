/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labirinto;

/**
 *
 * @author pedro
 */
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o tamanho do labirinto (NxN): ");
        int N = scanner.nextInt();
        System.out.print("Digite o número de obstáculos: ");
        int M = scanner.nextInt();

        Labirinto labirinto = new Labirinto(N, M);
        labirinto.imprimirLabirinto();

        Busca busca1 = new BFS();
        Busca busca2 = new DFS();

        System.out.println("\nBuscando a partir da entrada 1 com BFS...");
        busca1.buscar(labirinto.getGrid(), labirinto.getEntrada1(), labirinto.getSaida());

        System.out.println("\nBuscando a partir da entrada 2 com DFS...");
        busca2.buscar(labirinto.getGrid(), labirinto.getEntrada2(), labirinto.getSaida());
    }
}

