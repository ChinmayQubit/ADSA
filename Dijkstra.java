package org.example;

import java.util.Scanner;

public class Dijkstra
{
    // Function to find the vertex with minimum distance not yet visited
    static int findMinDistance(int[] dist, boolean[] visited, int n)
    {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < n; v++)
        {
            if (!visited[v] && dist[v] < min)
            {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Dijkstra's Algorithm
    static void dijkstraAlgo(int[][] graph, int src, int n)
    {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < n - 1; count++)
        {
            int u = findMinDistance(dist, visited, n);

            if (u == -1) // No more reachable vertices
            {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < n; v++)
            {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v])
                {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printDistances(dist, src, n);
    }

    // Function to print the shortest distances from the source
    static void printDistances(int[] dist, int src, int n)
    {
        System.out.println("Vertex\tDistance from Source " + src);

        for (int i = 0; i < n; i++)
        {
            if (dist[i] == Integer.MAX_VALUE)
            {
                System.out.println(i + "\t\t" + "INF");
            }
            else
            {
                System.out.println(i + "\t\t" + dist[i]);
            }
        }
    }

    // Main function
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter the adjacency matrix (" + n + "x" + n + "):");

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();

        dijkstraAlgo(graph, src, n);

        sc.close();
    }
}
/*OUTPUT:
Enter the number of vertices: 7
Enter the adjacency matrix (7x7):
0  10  0  0  0  30  0
0   0 20  0  0   0  0
0   0  0 15  5   0  0
25  0  0  0 12   0 20
0   0  0  0  0   0  7
0   0  0  0  0   0 35
0   0  0  0  0   0  0
Enter the source vertex: 0
Vertex	Distance from Source 0
0		0
1		10
2		30
3		45
4		35
5		30
6		42
 */