import java.util.ArrayList;
import java.util.List;

class samplingCoordinate {
    //sPoint = ? // Data set
    private List<Double> samplingSet = new ArrayList<Double>();
    //fs = ? //Frequency that we want to sampling data
    //return set of level data
    private double sampFreq;


    /**
     * constructor of samplingCoordinate
     */
    samplingCoordinate(){

    }

    samplingCoordinate(List<fourierTransform> heartRateInput, double samplingFreq,int n) {
        samplingSet = sampling(heartRateInput,samplingFreq, n);
    }

    /**
     * sampling method
     */
    public List<Double> sampling(List<fourierTransform> heartRateInput, double samplingFreq, int n) {

        List<double[]> samplingCoordinates = new ArrayList<double[]>();

        //the number of totalSampling
        //max delta(timer) * sampling freq.
        double firstTimer = heartRateInput.get(0).getTimer();
        double lastTimer = heartRateInput.get(heartRateInput.size() - 1).getTimer();
        System.out.println("First sampling = " + firstTimer + "\n Last sampling = " + lastTimer);
        int deltaTime = (int) (lastTimer - firstTimer);
        System.out.println("delta sampling = " + deltaTime);
        int totalSampling = (int) (deltaTime * samplingFreq);
        System.out.println("Total sampling = " + totalSampling);

        //sx ?
        //first time sampler
        double sampX = heartRateInput.get(0).getTimer();
        //System.out.println("sx(0) = " + sampX);

        int currentIndex = 0;
        //TODO Make it optimizer
        for (int i = 0; i < n; i++) {

            //System.out.println("sx(" + i + ")= " + sampX);
            if (sampX >= heartRateInput.get(currentIndex + 1).getTimer()) {
                currentIndex += 1;
                //System.out.println("current index = " + currentIndex);
            }
            //else if (currentIndex + 1 == heartRateInput.size()) break;

            //TODO get level from Linear between 2 sampling points
            double m = (heartRateInput.get(currentIndex + 1).getLevel() - heartRateInput.get(currentIndex).getLevel()) / (heartRateInput.get(currentIndex + 1).getTimer() - heartRateInput.get(currentIndex).getTimer());
            double c = (heartRateInput.get(currentIndex).getLevel() - m * heartRateInput.get(currentIndex).getTimer());
            double sampY = m * sampX + c;

            samplingCoordinates.add(new double[]{sampX, sampY});
            //System.out.println("Sampling point (" + i + ") = " + "(" + samplingCoordinates.get(i)[0] + "," + samplingCoordinates.get(i)[1] + ")");
            sampX += 1 / samplingFreq;
            //TODO List of level & sampling freq
            samplingSet.add(sampY);
        }
        //printSamplingTest(samplingCoordinates);
        //printArrayList(samplingSet);
        return samplingSet;

    }

    private void printSamplingTest(List<double[]> samplingCoordinates) {
        for(int i = 0 ; i<samplingCoordinates.size(); i++){
            System.out.printf("time:%.0f\t",samplingCoordinates.get(i)[0]);
            System.out.printf("level:%.0f\n",samplingCoordinates.get(i)[1]);
        }
    }

    public void printArrayList(List<Double> doubleList) {
        System.out.println(doubleList.size());
        for (Double temp : doubleList) {
            System.out.println(temp);
        }
    }
}


