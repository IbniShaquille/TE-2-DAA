import java.util.Random;

public class HamiltonianPathGenerator {
    private int V; // Number of vertices
    private int[][] adjacencyMatrix;
    private int[] path;

    public HamiltonianPathGenerator(int V) {
        this.V = V;
        this.adjacencyMatrix = generateRandomGraph();
        this.path = new int[V];
    }

    private int[][] generateRandomGraph() {
        int[][] graph = new int[V][V];
        Random random = new Random();

        // Generate a random graph with edges
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                // 70% chance of having an edge between vertices
                if (random.nextDouble() < 0.7) {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }

        return graph;
    }

    public void printAdjacencyMatrix() {
        System.out.println("Generated Adjacency Matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printHamiltonianPath() {
        if (hamiltonianPathUtil()) {
            System.out.println("\nHamiltonian Path found:");
            for (int i = 0; i < V; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println(path[0]);  // To complete the Hamiltonian cycle
        } else {
            System.out.println("\nNo Hamiltonian Path exists.");
        }
    }

    private boolean hamiltonianPathUtil() {
        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }

        // Start from the first vertex (index 0)
        path[0] = 0;
        if (!hamiltonianPathUtil(1)) {
            return false;
        }

        return true;
    }

    private boolean hamiltonianPathUtil(int pos) {
        if (pos == V) {
            // All vertices are visited
            return true;
        }

        for (int v = 1; v < V; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;

                if (hamiltonianPathUtil(pos + 1)) {
                    return true;
                }

                // Backtrack
                path[pos] = -1;
            }
        }

        return false;
    }

    private boolean isSafe(int v, int pos) {
        if (adjacencyMatrix[path[pos - 1]][v] == 0) {
            return false; // There is no edge between the last vertex in the path and v
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false; // Vertex is already included in the path
            }
        }

        return true;
    }

    // Getters
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public static void main(String[] args) {
        HamiltonianPathGenerator generator = new HamiltonianPathGenerator(16);
        generator.printAdjacencyMatrix();
        generator.printHamiltonianPath();
    }
}
