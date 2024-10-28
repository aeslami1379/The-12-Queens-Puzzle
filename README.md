# 12 Queens Puzzle Solution

This repository provides a Java-based solution to the 12 Queens Puzzle, an extension of the classic 8 Queens problem, where the goal is to place 12 queens on a 12x12 chessboard so that no two queens threaten each other. The project uses combinatorial optimization techniques and genetic algorithms, including random genotype creation, mutation, crossover, and fitness evaluation, to achieve optimal solutions.

## Files and Structure

- **`Coordinate.java`**: Defines a simple coordinate class to represent positions on the chessboard.
- **`Queens.java`**: Contains the core logic for solving the 12 Queens Puzzle. Key methods include:
  - `createGenotype()`: Generates random valid queen positions for a 12x12 board.
  - `insertMutation()`: Applies mutations to queen positions with a given probability, introducing genetic diversity.
  - `crossover()`: Implements crossover functionality to combine two parent genotypes and produce offspring.
  - `fitness()`: Calculates the fitness of a genotype based on queen conflicts, rewarding configurations with fewer conflicts.
- **`Tester.java`**: Provides a suite of tests to validate the functionality of each algorithm component, including genotype creation, mutation, crossover, and fitness evaluation.

## Getting Started

To run the solution:

1. Clone the repository.
2. Compile all Java files.
3. Run `Tester.java` to execute tests and view the results of each function in the console.

## Algorithms

The genetic algorithm approach implemented here includes:
- **Random Genotype Generation**: Starts with unique random queen positions.
- **Mutation**: Randomly alters queen positions to explore the search space.
- **Crossover**: Combines parent solutions to generate new configurations.
- **Fitness Evaluation**: Scores each configuration based on queen conflicts, aiming for a score of 66 (no conflicts).

## Example Output

The `Tester.java` file provides sample output for each function, showing the step-by-step progression of the algorithm.


