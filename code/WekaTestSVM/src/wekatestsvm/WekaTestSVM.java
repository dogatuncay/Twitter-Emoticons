/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wekatestsvm;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SMO;

/**
 *
 * @author coli
 */
public class WekaTestSVM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // Input data file and data selection
        BufferedReader breader = null;
        breader = new BufferedReader(new FileReader("/Users/coli/Dropbox/Private/Machine Learning/Project/Weka/data/TwitterData_300words.txt"));
        Instances train = new Instances(breader);
        Instances trainData = new Instances(train);
        Instances evalData = new Instances(train);
        train.setClassIndex(0);
        breader.close();
        
//        // Create Naive Bayes training 
        SMO nB = new SMO();
        nB.buildClassifier(train);
//        
//        // Create 10-fold evaluation data
        Evaluation eval = new Evaluation(train);
//        eval.crossValidateModel(nB, train, 2, new Random(1));
//        System.out.println(eval.toSummaryString("\nResults\n=====\n", true));
//        System.out.println("F-Score :" + eval.fMeasure(1) + "\nPrecision :" + eval.precision(1) + "\nRecall :" + eval.recall(1));

        // Create 2-fold evaludatoin 
        // data selected to be 
        for(int size = 0; size < 5; size ++){
            trainData = new Instances(train);
            evalData = new Instances(train);
            for(int i = trainData.numInstances() - 1 - (int)Math.pow(10, (size + 1))/2; i >= (int)Math.pow(10, (size + 1))/2; i --){
                trainData.delete(i);
                evalData.delete(i);
            }
            
            for(int i = trainData.numInstances() - 1; i >= 0; i --){
                if(i % 2 == 0) trainData.delete(i); 
                else evalData.delete(i);
            }
            trainData.setClassIndex(0);
            evalData.setClassIndex(0);
            nB.buildClassifier(trainData);
            
            //train error
            eval = new Evaluation(trainData);
            eval.evaluateModel(nB, trainData);
            System.out.println(eval.toSummaryString("\nResults\n=====\n", true));
            
            //eval error 
            eval = new Evaluation(evalData);
            eval.evaluateModel(nB, evalData);
            System.out.println(eval.toSummaryString("\nResults\n=====\n", true));
        }
    }
}
