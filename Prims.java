import java.util.Scanner;

public class Prims {

    // Function to perform Prim's MST algorithm
    static void PrimMST(int[][] graph, int V) {
        int[] key = new int[V];            // Key values used to pick minimum weight edge
        boolean[] mstSet = new boolean[V]; // To represent set of vertices included in MST
        int[] parent = new int[V];         // Array to store constructed MST

        // Initialize all keys as infinite and parents as -1
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            mstSet[i] = false;
        }

        key[0] = 0; // Start from vertex 0

        // MST will have V-1 edges
        for (int count = 0; count < V - 1; count++) {
            // Pick vertex u with minimum key not yet included in MST
            int u = FindMinKey(key, mstSet, V);
            if (u == -1) break; // Safety check for disconnected graph

            mstSet[u] = true;

            // Update key and parent for adjacent vertices of u
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST
        PrintMST(parent, graph, V);
    }

    // Function to find vertex with minimum key value not yet included in MST
    static int FindMinKey(int[] key, boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // Function to print MST stored in parent[]
    static void PrintMST(int[] parent, int[][] graph, int V) {
        int totalCost = 0;
        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println((parent[i] + 1) + " - " + (i + 1) + "    " + graph[i][parent[i]]);
                totalCost += graph[i][parent[i]];
            }
        }
        System.out.println("Total cost of MST: " + totalCost);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];
        System.out.printf("Enter the adjacency matrix (%dx%d):\n", V, V);

        // Input adjacency matrix, replace 0 (except diagonal) with INF
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
                if (i != j && graph[i][j] == 0) {
                    graph[i][j] = Integer.MAX_VALUE; // No edge
                }
            }
        }

        // Run Prim's algorithm
        PrimMST(graph, V);

        sc.close();
    }
}
/*OUTPUT:
Enter the number of vertices: 7
Enter the adjacency matrix (7x7):
0 28 0 0 0 10 0
28 0 16 0 0 0 14
0 16 0 12 0 0 0
0 0 12 0 22 0 18
0 0 0 22 0 25 24
10 0 0 0 25 0 0
0 14 0 18 24 0 0
Edge   Weight
3 - 2    16
4 - 3    12
5 - 4    22
6 - 5    25
1 - 6    10
2 - 7    14
Total cost of MST: 99
*/