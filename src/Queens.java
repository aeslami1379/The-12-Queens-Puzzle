import java.util.*;
import java.lang.Math;

public class Queens
{
    private static int boardSize = 12;
    static String problem = "12 queens";

    // creates a valid genotype with random values
    public static Integer [] createGenotype()
    {
        Integer [] genotype = new Integer [boardSize];
        Set<Integer> used = new HashSet<>();
        Random rand = new Random();

        // Fill the genotype with unique random values from 1 to boardSize
        for (int i = 0; i < boardSize; i++) {
            int value;
            do {
                value = rand.nextInt(boardSize) + 1; // Generate random value between 1 and boardSize
            } while (used.contains(value)); // Ensure uniqueness
            genotype[i] = value;
            used.add(value);
        }

        return genotype;
    }

    // move a gene in the genotype
    // the move happens with probability p, so if p = 0.8
    // then 8 out of 10 times this method is called, a move happens
    public static Integer[] insertMutation(Integer[] genotype, double p)
    {
        Random rand = new Random();

        for (int i = 0; i < boardSize; i++) {
            if (rand.nextDouble() < p) { // Mutation occurs with probability p
                // Randomly select a new position
                int newPosition = rand.nextInt(boardSize) + 1;
                genotype[i] = newPosition; // Mutate the gene
            }
        }
        return genotype;
    }

    // creates 2 child genotypes using the 'cut-and-crossfill' method
    public static Integer[][] crossover(Integer[] parent0, Integer[] parent1)
    {
        Integer[][] children = new Integer[2][boardSize];
        boolean[] usedInChild0 = new boolean[boardSize + 1]; // Track used elements in child0
        boolean[] usedInChild1 = new boolean[boardSize + 1]; // Track used elements in child1

        // Crossover point
        int crossoverPoint = boardSize / 2;

        // First half for both children
        for (int i = 0; i < crossoverPoint; i++) {
            children[0][i] = parent0[i];
            usedInChild0[parent0[i]] = true; // Mark as used in child0

            children[1][i] = parent1[i];
            usedInChild1[parent1[i]] = true; // Mark as used in child1
        }

        // Fill remaining parts for each child from the other parent
        fillRemainingPart(children[0], parent1, usedInChild0, crossoverPoint);
        fillRemainingPart(children[1], parent0, usedInChild1, crossoverPoint);


        return children;

    }

    // calculates the fitness of an individual
    public static int fitness(Integer [] genotype)
    {
        /* The initial fitness is the maximum pairs of queens
         * that can be in check (all possible pairs in check).
         * So we are using it as the maximum fitness value.
         * We deduct 1 from this value for every pair of queens
         * found to be in check.
         * So, the lower the score, the lower the fitness.
         * For a 12x12 board the maximum fitness is 66 (no checks),
         * and the minimum fitness is 0 (all queens in a line).
         */
        int fitness = (int) (0.5 * boardSize * (boardSize - 1));

        // Calculate the number of pairs of queens that are in check
        for (int i = 0; i < boardSize; i++) {
            for (int j = i + 1; j < boardSize; j++) {
                // Check if the queens are in the same row or diagonal
                if (genotype[i] == genotype[j] || Math.abs(i - j) == Math.abs(genotype[i] - genotype[j])) {
                    fitness--; // Deduct from fitness for each pair in check
                }
            }
        }
        return fitness;
    }

    //private helper method to fill remaining part of children
    private static void fillRemainingPart(Integer[] child, Integer[] otherParent, boolean[] used, int crossoverPoint) {
        int fillIndex0 = crossoverPoint; // child crossover point
        int fillIndex1 = crossoverPoint; // parent crossover point

        for (int i = 0; i < boardSize; i++) {
            // Fill child from other parent
            if (!used[otherParent[fillIndex1 % boardSize]]) {
                child[fillIndex0++] = otherParent[fillIndex1 % boardSize];
            }
            fillIndex1++;
        }
    }
}
