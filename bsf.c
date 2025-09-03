#include <stdio.h>
#include <stdlib.h>

#define MAX 10

int queue[MAX];         // Queue array
int front = -1, rear = -1;
int visited[MAX];       // Visited array

// Function to add a vertex to the queue
void enqueue(int vertex)
{
    if (rear == MAX - 1)
    {
        printf("Queue is Full\n");
        return;
    }
    if (front == -1)
    {
        front = 0;
    }
    queue[++rear] = vertex;
}

// Function to remove a vertex from the queue
int dequeue()
{
    if (front == -1)
    {
        return -1;
    }

    int vertex = queue[front];

    if (front == rear)
    {
        // Queue becomes empty
        front = -1;
        rear = -1;
    }
    else
    {
        front++;
    }

    return vertex;
}

// Breadth-First Search function
void BFS(int graph[MAX][MAX], int vertices, int start_vertex)
{
    // Initialize visited array
    for (int i = 0; i < vertices; i++)
    {
        visited[i] = 0;
    }

    enqueue(start_vertex);          // Start from the given vertex
    visited[start_vertex] = 1;

    printf("BFS Traversal: ");

    while (front != -1)
    {
        int current = dequeue();    // Dequeue a vertex
        printf("%d ", current);     // Print/visit the vertex

        // Enqueue all unvisited neighbors
        for (int i = 0; i < vertices; i++)
        {
            if (graph[current][i] == 1 && visited[i] == 0)
            {
                enqueue(i);
                visited[i] = 1;
            }
        }
    }

    printf("\n");
}

int main()
{
    int vertices, start;
    int graph[MAX][MAX];

    // Input number of vertices
    printf("Enter the Number of Vertices: ");
    scanf("%d", &vertices);

    // Input adjacency matrix
    printf("Enter the Adjacency Matrix (%d x %d):\n", vertices, vertices);
    for (int i = 0; i < vertices; i++)
    {
        for (int j = 0; j < vertices; j++)
        {
            scanf("%d", &graph[i][j]);
        }
    }

    // Input starting vertex
    printf("Enter the Starting Vertex (0 to %d): ", vertices - 1);
    scanf("%d", &start);

    BFS(graph, vertices, start);  // Perform BFS traversal

    return 0;
}
//OUTPUT
//Enter the Number of Vertices: 4
//Enter the Adjacency Matrix (4 x 4):
//0 1 0 1
//1 0 1 0
//0 1 0 1
//1 0 1 0
//Enter the Starting Vertex (0 to 3): 0
//BFS Traversal: 0 1 3 2