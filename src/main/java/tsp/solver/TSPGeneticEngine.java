/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.solver;

import emo.Individual;
import emo.OptimizationProblem;
import engines.NSGA2Engine;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import parsing.IndividualEvaluator;
import utils.RandomNumberGenerator;

/**
 *
 * @author Haitham Seada
 */
public class TSPGeneticEngine extends NSGA2Engine {

    private final int n;

    public TSPGeneticEngine(
            OptimizationProblem optimizationProblem,
            IndividualEvaluator individualEvaluator,
            int n) {
        super(optimizationProblem, individualEvaluator);
        this.n = n;
    }

    @Override
    public Individual[] generateInitialPopulation(String displayMessage) {
        // Create a population of your own individuals
        TSPSolution[] population
                = new TSPSolution[optimizationProblem.getPopulationSize()];
        for (int i = 0; i < population.length; i++) {
            population[i] = new TSPSolution(
                    optimizationProblem,
                    individualEvaluator,
                    n);
            individualEvaluator.updateIndividualObjectivesAndConstraints(
                    optimizationProblem,
                    population[i]);
        }
        return population;
    }

    /**
     *
     * @param parent1DeepCopy
     * @param parent2DeepCopy
     */
    @Override
    protected void crossCustomVariables(
            Individual parent1DeepCopy,
            Individual parent2DeepCopy) {
//        TSPSolution tspParent1 = (TSPSolution) parent1DeepCopy;
//        TSPSolution tspParent2 = (TSPSolution) parent2DeepCopy;
//        // Pick your cut
//        int cutStart, cutEnd;
//        do {
//            cutStart = RandomNumberGenerator.nextInt(0, n-1);
//            cutEnd = RandomNumberGenerator.nextInt(cutStart + 1, n-1);
//        } while (cutStart >= cutEnd || cutEnd == n);
//        // Store the cities in the cut you picked
//        Set<Integer> cutCitiesSet = new LinkedHashSet<>();
//        for (int cityIndex = cutStart; cityIndex <= cutEnd; cityIndex++) {
//            cutCitiesSet.add(tspParent1.getPath().get(cityIndex));
//        }
//        // Concatenate the same set of cities in the other parent
//        List<Integer> cutCitiesList = new ArrayList<>(cutCitiesSet);
//        List<Integer> crossedPath = new ArrayList<>();
//        int i;
//        for (i = 0; i < tspParent2.getPath().size(); i++) {
//            if(cutCitiesSet.contains(tspParent2.getPath().get(i))) {
//                for (int j = 0; j < cutCitiesList.size(); j++) {
//                    crossedPath.add(cutCitiesList.get(j));
//                }
//                break;
//            } else {
//                crossedPath.add(tspParent2.getPath().get(i));
//            }
//        }
//        // Complete the crossed path
//        for (int j = i + 1; j < tspParent2.getPath().size(); j++) {
//            if(!cutCitiesSet.contains(tspParent2.getPath().get(j))) {
//                crossedPath.add(tspParent2.getPath().get(j));
//            }
//        }
    }

    @Override
    protected void mutateCustomVariables(Individual individual) {
        TSPSolution sol = (TSPSolution) individual;
        for (int i = 1; i < sol.getPath().size(); i++) {
            if (RandomNumberGenerator.next()
                    < optimizationProblem.getCustomMutationProbability()) {
                int moveToIndex = -1;
                do {
                    moveToIndex = RandomNumberGenerator.nextInt(
                            1,
                            sol.getPath().size() - 1);
                } while (moveToIndex != -1
                        && moveToIndex != 0
                        && moveToIndex != i);
            }
        }
    }
}
