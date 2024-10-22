/*
* This is the K-Means Clustering class. 
* Here we will be executing the K-Means Clustering algorithm.
* In this class we have the following global variables:
*       a) clusters                 : a HashMap with our k-numbered teams(clusters) and their points.
*       b) means                    : an ArrayList with our k-representatives(means). 
*                                   (double[0] = coordinates of x1 and double[1] = coordinates of x2)
*       c) data                     : an ArrayList with the data.
*       d) M                        : a static integer that represents the number of clusters (M).
*       e) stabilityCounter         : a integer value that keeps the number of times the representatives/means stayed the same. 
*                                   (will be used to determine the termination of the algorithm)
*
* In this class we have the following methods:
*       a) initialiseClusters()     : a method that is used to initialise the clusters and the representatives/means.
*       b) addDataToClusters()      : a method that each time it is called, adds data to corresponding clusters based on the minimum euclidian 
*                                   distance from the clusters' representatives/means.
*       c) findEuclidianDistance()  : a method that finds the euclidian distance between a mean and a point. 
*                                   (Inputs are the cluster number - in order to get the right mean - and the point)
*       d) copyArray()              : a method that is used to create a deep copy of the clusterMeans HashMap. We use this to create the clusterMeansHistory HashMap.
*       e) recenterMeans()          : a method that is used to recenter the means (recalculates the clusters' centers)
*       f) allowTermination()       : a method that runs through all the criteria for the algorithm to terminate. If the criteria are met -> returns true else false.
*       g) countError()             : a method that counts the total error. (sfalma omadopoihshs)
*
*       we also have some getters that help us in our Main class.
* ----------------------------------
*/
import java.util.*;

public class KMeansClustering {
    
    private HashMap<Integer,ArrayList<double[]>> clusters = new HashMap<Integer,ArrayList<double[]>>();
    private ArrayList<double[]> means = new ArrayList<double[]>();
    private ArrayList<double[]> data = new ArrayList<double[]>();

    private static int M;
    private int stabilityCounter = 0;

    public KMeansClustering(int m, ArrayList<double[]> data){
        
        ArrayList<double[]> meansHistory = new ArrayList<double[]>();

        this.M = m;
        this.data = data;

        initialise();
        while(true){ 
            addDataToClusters();
            meansHistory = copyArray(means);
            recenterMeans();
            if(allowTermination(meansHistory)){
                break;
            }
        }
    }

    private void initialise(){
        /* 
        * Here we will be enumerating all the data and shuffling the enumeration.
        * This shuffling happens in order to chose random data to set as our starting points.
        * (we will be picking the first random integers in the shuffled ArrayList) 
        */
        ArrayList<Integer> generatedNumbers = new ArrayList<Integer>();
        for(int i=0; i<data.size(); i++) { 
            generatedNumbers.add(i); 
        }
        Collections.shuffle(generatedNumbers);
        
        /* Initialisations. */
        for(int i=0; i<M; i++) {
            ArrayList<double[]> empty = new ArrayList<double[]>();
            clusters.put(i,empty);

            double[] randomPoint = data.get(generatedNumbers.get(i));
            double x1 = randomPoint[0];
            double x2 = randomPoint[1];
            double[] newPoint = {x1,x2};
            means.add(newPoint);
        }
    }

    private void addDataToClusters(){   
        /* 
        * Clearing the clusters in order to prevent 
        * multiple additions of the same data. 
        */
        for(int i: clusters.keySet()){
            clusters.get(i).clear();
        }

        for(double[] d : data){

            double minDistance = Double.MAX_VALUE;
            int closestClusterNumber = Integer.MAX_VALUE;

            for(int i=0; i<M; i++){
                double distance = findEuclidianDistance(i,d);
                if(distance < minDistance){ 
                    minDistance = distance;
                    closestClusterNumber = i;
                }  
            }
            clusters.get(closestClusterNumber).add(d);
        }
    }

    private double findEuclidianDistance(int clusterNumber, double[] point){  
        double[] meanCoordinates = means.get(clusterNumber);
        double meanCoordX = meanCoordinates[0];
        double meanCoordY = meanCoordinates[1];
        double dataCoordX = point[0];
        double dataCoordY = point[1];
        double substractionX = Math.abs(dataCoordX - meanCoordX);
        double substractionY = Math.abs(dataCoordY - meanCoordY);
        double euclidianDistance = Math.sqrt(Math.pow(substractionX,2) + Math.pow(substractionY,2));
        
        return euclidianDistance;
    }

    private ArrayList<double[]> copyArray(ArrayList<double[]> original){
        ArrayList<double[]> copy = new ArrayList<double[]>();
        
        for(double[] entry : original){
            double[] point = new double[2];
            point[0] = entry[0];
            point[1] = entry[1];
            copy.add(point);
        }

        return copy;
    }

    private void recenterMeans(){
        for(int i : clusters.keySet()){
            double totalX1 = 0;
            double totalX2 = 0;
            for(double[] d : clusters.get(i)){
                totalX1 += d[0];
                totalX2 += d[1];
            }
            means.get(i)[0] = totalX1/(double)clusters.get(i).size();
            means.get(i)[1] = totalX2/(double)clusters.get(i).size();
        }
    }

    private boolean allowTermination(ArrayList<double[]> meansHistory){
        boolean[] stabilities = new boolean[M];
        int stabilityDetector = 0;

        for(int i=0; i<M; i++){
            boolean condition1 = (means.get(i)[0] == meansHistory.get(i)[0]);
            boolean condition2 = (means.get(i)[1] == meansHistory.get(i)[1]);
            if(condition1 && condition2){
                stabilities[i] = true;
                stabilityDetector++;
            }else{
                stabilities[i] = false;
                stabilityCounter = 0;
            }
        }

        if(stabilityDetector == M && stabilityCounter>=2){
            return true;
        }
        else if(stabilityDetector == M && stabilityCounter<2){
            stabilityCounter++;
            return false;
        }
        else{
            return false;
        }
    }

    public double countError(){
        double errorCount = 0;
        for(int i=0; i<M; i++){
            for(double[] d : clusters.get(i)){
                errorCount += findEuclidianDistance(i,d);
            }
        }
        return errorCount;
    }

    public HashMap<Integer, ArrayList<double[]>> getClusters(){
        return clusters;
    }
    
    public ArrayList<double[]> getMeans(){
        return means;
    } 
}
