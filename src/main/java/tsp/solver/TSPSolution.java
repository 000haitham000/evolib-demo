/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.solver;

import emo.Individual;
import emo.OptimizationProblem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import parsing.IndividualEvaluator;
import utils.RandomNumberGenerator;

/**
 * TSP individual (candidate solution)
 *
 * @author Haitham
 */
public class TSPSolution extends Individual {

    private List<Integer> path;

    public TSPSolution(
            OptimizationProblem problem,
            IndividualEvaluator individualEvaluator,
            int n) {
        super(problem, individualEvaluator);
        path = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            path.add(i);
        }
        // Shuffle cities except for start and end cities
        Set<Integer> manipulatedIndices = new HashSet<>();
        for (int i = 1; i < n; i++) {
            if (!manipulatedIndices.contains(i)) {
                int moveToIndex = RandomNumberGenerator.nextInt(1, n - 1);
                manipulatedIndices.add(moveToIndex);
                // Swap the positions of the two cities in the new list
                int temp = path.get(i);
                path.set(i, path.get(moveToIndex));
                path.set(moveToIndex, temp);
            }
        }
    }

    /**
     * @return the path
     */
    public List<Integer> getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(List<Integer> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        if (this.objectiveFunction.length == 1) {
            return String.format(
                    "TSPSolution{path = %s} - {obj-1 = %5.3f}",
                    path,
                    this.getObjective(0));
        } else {
            return String.format(
                    "TSPSolution{path = %s, x = %5.3f} - {obj-1 = %5.3f, obj-2 = %5.3f}",
                    path,
                    this.real[0],
                    this.getObjective(0),
                    this.getObjective(1));
        }
    }
}
