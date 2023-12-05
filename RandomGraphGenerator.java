import java.util.Random;

public class RandomGraphGenerator {

    private static Random random = new Random();

    public static int[][] generateRandomGraph(int numVertices, double edgeProbability) {
        int[][] graph = new int[numVertices][numVertices];

        // Generate a random graph with edges
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (random.nextDouble() < edgeProbability) {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }

        return graph;
    }

    public static void printGraph(int[][] graph) {
        System.out.println("Generated Adjacency Matrix:");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numVertices = 100;
        double edgeProbability = 0.3; // Adjust this value based on your preference

        int[][] randomGraph = generateRandomGraph(numVertices, edgeProbability);
        printGraph(randomGraph);
    }
}
