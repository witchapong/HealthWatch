import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * initial inputStreamData
 * double[] inputFFT  = {90,65,64,90,87,98,96,62,45,65,70,82,81,89,76,83};
 * double[] initialInput = {1,1,1,1,0,0,0,0};
 * //
 * double[] ansTransform = ans.FastFourier(inputFFT);
 * double[] ansTransform = ans.FastFourier(initialInput);
 * for (int i = 0; i < ansTransform.length; i++) {
 * double anAnsTransform = ansTransform[i];
 * System.out.print("\n"+"[" + i +"] " + String.valueOf(anAnsTransform) + "\t");
 * }
 */

class fourierTransform {
    private double timer, level;

    public fourierTransform(double x, double y) {
        this.timer = x;
        this.level = y;
    }

    public fourierTransform() {

    }

    /* method spilt input data */
    public List<fourierTransform> splitData(List<String> dataInput) {
        List<fourierTransform> temp = new ArrayList<fourierTransform>();
        for (String aDataInput : dataInput) {
            String[] tempString = aDataInput.split(";", 19);
            //TODO keep in ms. * 10^(-6)
            timer = TimeUnit.NANOSECONDS.toSeconds(Long.parseLong(tempString[0]));
            level = Double.parseDouble(tempString[1]);
            temp.add(new fourierTransform(timer, level));
        }
        return temp;
    }


    /* method Fast Fourier Transform */
    public double[] FastFourier(double[] input) {

        int length = input.length;
        double[] tempInput = Arrays.copyOf(input, length);
        FastFourierTransformer fastFourierTransformer = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] complexes = fastFourierTransformer.transform(tempInput, TransformType.FORWARD);

        for (int i = 0; i < complexes.length; i++) {
            Complex complexe = complexes[i];
            double real = (complexe.getReal());
            double img = (complexe.getImaginary());
            //System.out.println("[ "+String.valueOf(real) + " , " + String.valueOf(img)+"i ]");
            tempInput[i] = Math.sqrt((real * real) + (img * img));
            System.out.println("Freq Domain: "+ tempInput[i]);
        }

        return tempInput;
    }


    public void printArrayList(ArrayList<fourierTransform> args) {
        for (fourierTransform temp : args) {
            System.out.println(String.valueOf(temp.getTimer()) + "," + String.valueOf(temp.getLevel()));

        }
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }
}
