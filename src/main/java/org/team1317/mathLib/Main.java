package org.team1317.mathLib;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Main {
    public static void main(String[] args) {
        int numPoints = 200;
        double xData[] = new double[numPoints];
        double yData[] = new double[numPoints];

        TrapezoidProfiler profile = new TrapezoidProfiler(5.0, 1.0);
        double currentPosition = 0.0;
        double currentVelocity = 0.0;

        double goalPosition = 10.0;

        profile.setGoal(goalPosition, 0.0); // Example goal position and velocity
        double step = goalPosition / ((double)(numPoints));
        for(int i = 0; i < numPoints; i++) {
           
            double x = i  * step;
            currentVelocity = profile.calculate(currentPosition, currentVelocity, step);
            currentPosition += currentVelocity * step;
            xData[i] = x;
            yData[i] = currentVelocity;

            if(currentPosition >= goalPosition) {
                System.out.println("Reached goal position at x = " + x);
                break;
            }
        }

        XYChart chart = QuickChart.getChart("profile", "X", "Y", "f(x)", xData, yData);
        new SwingWrapper(chart).displayChart();

        double xDataDerivitive[] = new double[numPoints];
        double yDataDerivitive[] = new double[numPoints];
        for(int i = 0; i < numPoints; i++) {
            xDataDerivitive[i] = xData[i];
            yDataDerivitive[i] = (yData[i] - (i > 0 ? yData[i-1] : 0)) / step;
        }
        XYChart chart2 = QuickChart.getChart("derivative", "X", "Y", "f'(x)", xDataDerivitive, yDataDerivitive);
        new SwingWrapper(chart2).displayChart();
    }
}
