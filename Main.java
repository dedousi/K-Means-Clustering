/* 
This is the Main class of the K-means Clustering.
Here we will be executing our algorithm (30 times per cluster count M).
And we will be creating files with important information that will 
be later used to create the plots of the algorithm.
*/

import java.util.*;
import java.io.*;

public class Main{    
    public static void main(String[] args){
        
        /*
        * HashMaps to keep the information for each of the 20 times the program was executed.
        * recordedClustersPerLoop : keeps information for the clusters that each loop created.
        * recordedMeansPerLoop : keeps information for the cluster Means that each loop had in the end.
        * The unique error result of each loop was used as a key to the HashMaps.
        */
        HashMap<Double, HashMap<Integer, ArrayList<double[]>>> recordedClustersPerLoop = new HashMap<Double, HashMap<Integer, ArrayList<double[]>>>();
        HashMap<Double, ArrayList<double[]>> recordedMeansPerLoop = new HashMap<Double, ArrayList<double[]>>();
        
        // The data set.
        DataRandomizer dataRandomizer = new DataRandomizer();
        ArrayList<double[]> data = dataRandomizer.getGeneratedData();
        
        int[] M = {3,5,13}; // Number of clusters (M).
        
        for(int i : M){
            try{
                // File Creation.
                String fileName = "results\\Results_for_"+i+"_clusters.txt";
                File file = new File(fileName);
                if(file.createNewFile()){
                    System.out.println("File with min.error data for M="+i+" "+fileName+" has been created.");
                }else{ 
                    System.out.println("The file "+fileName+" already exists."); 
                }

                /* 
                * Running the K-Means Clustering (30 times for each M) and Writing in the Files.
                * In the files we will write the following information:
                *       a) The errors of each loop in order to check if our result is indeed correct.
                *       b) The minimum error (our result).
                *       c) The result's cluster means.
                *       d) The result's cluster sizes-number of points in them.
                *       e) The result's cluster contents.
                */
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write("The clusters were M="+i+"\n");
                for(int j=0; j<30; j++){
                    
                    KMeansClustering kmeans = new KMeansClustering(i,data);      // The K-Means Clustering.
                    double error = kmeans.countError();                          // Getting the error result.
                    
                    fileWriter.write("The error for loop "+j+" was: "+error+"\n");
                    recordedMeansPerLoop.put(error,kmeans.getMeans());
                    recordedClustersPerLoop.put(error,kmeans.getClusters()); 
                }
                fileWriter.write("--------------------\n");

                // Finding the minimum error of those 20 K-Means Clusterings.
                double minError = Double.MAX_VALUE;
                for(double d : recordedMeansPerLoop.keySet()){
                    if(minError>d){ minError = d;}
                }

                // Writing down the data for the minimum error.
                fileWriter.write("The min.error was "+ minError+"\n--------------------\n");
                fileWriter.write("The representatives are:\n");
                for(int h=0; h<i; h++){
                    double x = recordedMeansPerLoop.get(minError).get(h)[0];
                    double y = recordedMeansPerLoop.get(minError).get(h)[1];
                    fileWriter.write("The means for cluster "+h+" is: ("+x+","+y+")\n");
                }
                fileWriter.write("-------------------\n");
                for(int k=0; k<i; k++){
                    int cluster_size = recordedClustersPerLoop.get(minError).get(k).size();
                    fileWriter.write("cluster "+k+" has "+cluster_size+" points.\n");
                }
                fileWriter.write("-------------------\n");
                for(int l : recordedClustersPerLoop.get(minError).keySet()){
                    for(double[] d: recordedClustersPerLoop.get(minError).get(l)){
                        fileWriter.write("("+d[0]+","+d[1]+")\n");
                    }
                }
                fileWriter.close();
            }
            catch (IOException e){ 
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }   
    }
}