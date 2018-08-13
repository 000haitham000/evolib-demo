/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.solver;

import emo.DoubleAssignmentException;
import emo.Individual;
import emo.OptimizationProblem;
import engines.AbstractGeneticEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.stream.XMLStreamException;
import parsing.IndividualEvaluator;
import parsing.InvalidOptimizationProblemException;
import parsing.StaXParser;

/**
 *
 * @author Haitham Seada
 */
public class TSPSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws
            IOException,
            XMLStreamException,
            InvalidOptimizationProblemException,
            FileNotFoundException,
            DoubleAssignmentException {
        // Read problem
        URL url = TSPSolver.class.getClassLoader().getResource(
                "tsp.xml");
        InputStream in = url.openStream();
        OptimizationProblem optimizationProblem = StaXParser.readProblem(in);
        // Define the number of cities
        int n = 10;
        // Create Evaluator
        IndividualEvaluator individualEvaluator = new TSPEvaluator(
                optimizationProblem,
                n,
                TSPEvaluator.SYMMETRIC_SCENARIO_1);
        // Create Engine
        AbstractGeneticEngine geneticEngine = new TSPGeneticEngine(
                optimizationProblem,
                individualEvaluator,
                n);
        // Specify output directory
        File outDir = new File(System.getProperty("user.home") + "/evolib-demo");
        outDir.mkdirs();
        // Optimize
        Individual[] finalPopulation = geneticEngine.start(outDir, 0, 0);
        // Display Results
        display(finalPopulation);
    }

    private static void display(Individual[] tspPop) {
        for (Individual tspSol : tspPop) {
            System.out.println(tspSol.toString());
        }
    }
}
