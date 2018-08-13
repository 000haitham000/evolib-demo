/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.solver;

import emo.Individual;
import emo.OptimizationProblem;
import parsing.IndividualEvaluator;
import utils.RandomNumberGenerator;

/**
 *
 * @author Haitham
 */
public class TSPEvaluator extends IndividualEvaluator {

    public static final int SYMMETRIC_LINEAR_EQUIDISTANT = 0;
    public static final int SYMMETRIC_RANDOM = 1;
    public static final int SYMMETRIC_SCENARIO_1 = 2;

    private final double[][] odMatrix;

    public TSPEvaluator(OptimizationProblem problem, int n, int mode) {
        super(problem);
        this.odMatrix = new double[n][n];
        if (mode == SYMMETRIC_LINEAR_EQUIDISTANT) {
            // Initializes the OD matrix according to the follwing rules:
            //  1 - The matrix is symmetric
            //  2 - The cost of moving from one city to itself is Zero
            //  3 - All cities are assumed to lie on the same straight line
            //  4 - All cities are equidistant
            for (int i = 0; i < this.odMatrix.length; i++) {
                for (int j = i + 1; j < this.odMatrix[i].length; j++) {
                    this.odMatrix[i][j] = j - i;
                    this.odMatrix[j][i] = j - i;
                }
            }
        } else if (mode == SYMMETRIC_RANDOM) {
            // Initializes the OD matrix according to the follwing rules:
            //  1 - The matrix is symmetric
            //  2 - Weights are random real numbers between 1 and 10
            for (int i = 0; i < this.odMatrix.length; i++) {
                for (int j = i + 1; j < this.odMatrix[i].length; j++) {
                    double cost = RandomNumberGenerator.next(1, 10);
                    this.odMatrix[i][j] = cost;
                    this.odMatrix[j][i] = cost;
                }
            }
        } else if (mode == SYMMETRIC_SCENARIO_1) {
            // Initializes the OD matrix according to the follwing rules:
            //  1 - The matrix is symmetric
            //  2 - The cost of moving from one city to itself is picked from
            //      a set of fixed values
            int[] fixedValues = new int[5];
            for (int i = 0; i < fixedValues.length; i++) {
                fixedValues[i] = i + 1;
            }
            for (int i = 0; i < this.odMatrix.length; i++) {
                for (int j = i + 1; j < this.odMatrix[i].length; j++) {
                    double cost = fixedValues[RandomNumberGenerator.nextInt(
                            0,
                            fixedValues.length - 1)];
                    this.odMatrix[i][j] = cost;
                    this.odMatrix[j][i] = cost;
                }
            }
        }
    }

    @Override
    public void updateIndividualObjectivesAndConstraints(
            OptimizationProblem problem,
            Individual individual) {
        super.updateIndividualObjectivesAndConstraints(problem, individual);
        TSPSolution sol = (TSPSolution) individual;
        double totalCost = 0;
        double maxLegCost = Double.MIN_VALUE;
        if (sol.getPath() != null) {
            for (int i = 0; i < sol.getPath().size(); i++) {
                int originCity = sol.getPath().get(i);
                int destinationCity;
                if (i == sol.getPath().size() - 1) {
                    destinationCity = sol.getPath().get(0);
                } else {
                    destinationCity = sol.getPath().get(i + 1);
                }
                double legCost = odMatrix[originCity][destinationCity];
                if (legCost > maxLegCost) {
                    maxLegCost = legCost;
                }
                totalCost += legCost;
            }
        }
        sol.setObjective(0, totalCost);
        sol.setObjective(1, maxLegCost);
    }

    public int getN() {
        return odMatrix.length;
    }

    /**
     * @param o
     * @param d
     * @return the odMatrix
     */
    public double getODCost(int o, int d) {
        return odMatrix[o][d];
    }

    /**
     * @param i
     * @param j
     * @param cost
     */
    public void setODCost(int i, int j, double cost) {
        odMatrix[i][j] = cost;
        odMatrix[j][i] = cost;
    }
}
