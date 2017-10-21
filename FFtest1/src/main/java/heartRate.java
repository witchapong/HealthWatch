import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class heartRate {

    private static final File file = new File("/Users/Arianna/Desktop/testFile.txt");
    private static fourierTransform fourierTransform;
    private static samplingCoordinate samplingCoordinate = new samplingCoordinate();
    //TODO If define object of class but does not new Class ?
    private static heartRate heartRate = new heartRate();

    private heartRate() {
        fourierTransform = new fourierTransform();
    }

    public static void main(String[] args) {

        List<String> inputStream = new ArrayList<String>();

        //TODO array point (t,F)
        //TODO 2Dimension x-y data set
        //TODO Freq sampling method

        FileReader fileInput = null;
        BufferedReader bufferInput = null;
        try {

            fileInput = new FileReader(file);
            bufferInput = new BufferedReader(fileInput);
            String currentLine;
            while ((currentLine = bufferInput.readLine()) != null) {
                inputStream.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferInput != null) bufferInput.close();
                if (fileInput != null) fileInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //heartRate.printInputFile(inputStream);
        List<fourierTransform> heartRateInput = fourierTransform.splitData(inputStream);

        //System.out.println("\ninputstream: " + inputStream.get(1));
        //System.out.println("\n1st element of heartRateInput: " + String.valueOf(heartRateInput.get(1).getTimer() + "," + heartRateInput.get(1).getLevel()));

        //TODO heartRateInput & sampling rate ?
        //samplingCoordinate = new samplingCoordinate(heartRateInput,1, 256);
        //double output = fourierTransform.FastFourier(new samplingCoordinate(heartRateInput,1, 256));
        List<Double> samplingData = samplingCoordinate.sampling(heartRateInput,1,256);
        double[] test = new double[samplingData.size()];
        for( int i=0; i< test.length ; i++){
            test[i] = samplingData.get(i);
        }
        fourierTransform.FastFourier(test);
    }

    /* method printInputFile */
    private void printInputFile(List<String> stringArrayList) {
        for (String aStringArrayList : stringArrayList) {
            System.out.println(aStringArrayList);
        }
    }
}
