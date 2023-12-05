// Java program for the above approach
class HamiltonianPathDP {

    // Function to check whether there
    // exists a Hamiltonian Path or not
    static boolean Hamiltonian_path(int adj[][], int N) {
        boolean dp[][] = new boolean[N][(1 << N)];

        // Set all dp[i][(1 << i)] to
        // true
        for (int i = 0; i < N; i++)
            dp[i][(1 << i)] = true;

        // Iterate over each subset
        // of nodes
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {

                // If the jth nodes is included
                // in the current subset
                if ((i & (1 << j)) != 0) {

                    // Find K, neighbour of j
                    // also present in the
                    // current subset
                    for (int k = 0; k < N; k++) {

                        if ((i & (1 << k)) != 0 &&
                                adj[k][j] == 1 && j != k &&
                                dp[k][i ^ (1 << j)]) {

                            // Update dp[j][i]
                            // to true
                            dp[j][i] = true;
                            break;
                        }
                    }
                }
            }
        }

        // Traverse the vertices
        for (int i = 0; i < N; i++) {

            // Hamiltonian Path exists
            if (dp[i][(1 << N) - 1])
                return true;
        }

        // Otherwise, return false
        return false;
    }

    // Driver Code
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        /*
         * Generator random graph path function
         * small for 16 vertices
         * medium for 18 vertices
         * large for 20 vertices
         * uncomment the one you want to use
         */
        int[][] randomSmallAdjMatrix = RandomGraphGenerator.generateRandomGraph(16, 0.3);
        // int[][] randomMediumAdjMatrix = RandomGraphGenerator.generateRandomGraph(18, 0.3);
        // int[][] randomLargeAdjMatrix = RandomGraphGenerator.generateRandomGraph(20, 0.3);

        /*
         * Generator hamiltonian path function
         * The output always True
         * Small for 16 vertices
         * Medium for 18 vertices
         * Large for 20 vertices
         */
        // HamiltonianPathGenerator smallAdjMatrix = new HamiltonianPathGenerator(16);
        // HamiltonianPathGenerator mediumAdjMatrix = new HamiltonianPathGenerator(18);
        // HamiltonianPathGenerator largeAdjMatrix = new HamiltonianPathGenerator(20);

        // Function Call
        System.gc();
        long beforeUsedMemory = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();

        /*
         * uncomment the one you want to use
         */

        boolean isHamiltonianPath = Hamiltonian_path(randomSmallAdjMatrix, 16);
        // boolean isHamiltonianPath = Hamiltonian_path(randomMediumAdjMatrix, 18);
        // boolean isHamiltonianPath = Hamiltonian_path(randomLargeAdjMatrix, 20);

        // boolean isHamiltonianPath = Hamiltonian_path(smallAdjMatrix.getAdjacencyMatrix(), 16);
        // boolean isHamiltonianPath = Hamiltonian_path(mediumAdjMatrix.getAdjacencyMatrix(), 18);
        // boolean isHamiltonianPath = Hamiltonian_path(largeAdjMatrix.getAdjacencyMatrix(), 20);

        long endTime = System.currentTimeMillis();
        long afterUsedMemory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Memory used: " + (afterUsedMemory - beforeUsedMemory) / 1000000 + " MB");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");


        // Print the result
        if (isHamiltonianPath) {
            System.out.println("Hamiltonian Path exists");
        } else {
            System.out.println("Hamiltonian Path does not exist");
        }
    }
}

// This code is contributed by Kingash
