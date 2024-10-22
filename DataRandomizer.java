/* 
* This is the DataRandomizer class.
* In this class we use the method generatorForGivenSquare() in order 
* to generate a random number of points in a given space (space was given by professor).
*/
import java.util.*;

public class DataRandomizer {
    private ArrayList<double[]> generatedData = new ArrayList<double[]>();

    public DataRandomizer(){
        generatorForGivenSquare(150, 0.75, 1.25, 0.75, 1.25);
        generatorForGivenSquare(150, 0.0, 0.5, 0.0, 0.5);
        generatorForGivenSquare(150, 0.0, 0.5, 1.5, 2.0);
        generatorForGivenSquare(150, 1.5, 2.0, 0.0, 0.5);
        generatorForGivenSquare(150, 1.5, 2.0, 1.5, 2.0);
        generatorForGivenSquare(150, 0.0, 2.0, 0.0, 2.0); 
        generatorForGivenSquare(75, 0.6, 0.8, 0.0, 0.4);
        generatorForGivenSquare(75, 0.6, 0.8, 1.6, 2.0);
        generatorForGivenSquare(75, 1.2, 1.4, 0.0, 0.4);
        generatorForGivenSquare(75, 1.2, 1.4, 1.6, 2.0);
    }

    private void generatorForGivenSquare(int quantity, double minForX1, double maxForX1, double minForX2, double maxForX2){

        for(int i=0; i<quantity; i++){
            
            double x1 = Math.random() * (maxForX1 - minForX1) + minForX1;   // generate random X1.
            double x2 = Math.random() * (maxForX2 - minForX2) + minForX2;   // generate random X2.

            double[] point = {x1,x2};                                   // create an array that contains the two points.
            generatedData.add(point);                                   // insert point into the arraylist.
        }
    }

    public ArrayList<double[]> getGeneratedData(){
        return generatedData;
    }
}