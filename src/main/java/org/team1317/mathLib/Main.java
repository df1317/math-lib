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

        profile.setGoal(10.0, 0.0); // Example goal position and velocity

        for(int i = 0; i < numPoints; i++) {
            double step = 0.02;
            double x = (i/20.0);
            currentVelocity = profile.calculate(currentPosition, currentVelocity, step);
            currentPosition += currentVelocity * step;
            xData[i] = x;
            yData[i] = currentVelocity;
        }

        XYChart chart = QuickChart.getChart("profile", "X", "Y", "f(x)", xData, yData);
        new SwingWrapper(chart).displayChart();
    }   
}
