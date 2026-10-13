package org.team1317.mathLib;

public class TrapezoidProfiler implements Profiler {
    private double maxVelocity;
    private double maxAcceleration;

    private double goalVelocity = 0.0;
    private double goalPosition = 0.0;

    public TrapezoidProfiler(double maxVelocity, double maxAcceleration) {
        this.maxVelocity = maxVelocity;
        this.maxAcceleration = maxAcceleration;
    }

    @Override
    public void setGoal(double goalPosition, double goalVelocity) {
        this.goalPosition = goalPosition;
        this.goalVelocity = goalVelocity;
    }

    @Override
    public double calculate(double currentPosition, double currentVelocity, double deltaSeconds) {
        double posIfDecel = whereWouldIBeIfIStartDeceleratingNow(currentPosition, currentVelocity, deltaSeconds);
        System.out.println("posIfDecel: " + posIfDecel + " currentVelocity: " + currentVelocity); 
       if( posIfDecel >= goalPosition) {
            // we need to decelerate
            return currentVelocity - maxAcceleration * deltaSeconds;
        } else {
            // we can accelerate
            double nextVelocity = currentVelocity + maxAcceleration * deltaSeconds;
            nextVelocity = Math.clamp(nextVelocity, -maxVelocity, maxVelocity);
            return nextVelocity;
        }
    }
    private double whereWouldIBeIfIStartDeceleratingNow(double currentPosition, double currentVelocity, double deltaSeconds) {
        while(currentVelocity > 0) {
            currentPosition += currentVelocity * deltaSeconds;
            currentVelocity -= maxAcceleration * deltaSeconds;
        }
        return currentPosition;
    }

    // maybe useful:
    //  double accelerationTime = maxVelocity / maxAcceleration;
    //     double accelerationDistance = 0.5 * maxAcceleration * Math.pow(accelerationTime, 2);

    //     double totalDistance = Math.abs(goalPosition - currentPosition);
    //     double cruiseDistance = totalDistance - 2 * accelerationDistance;

    //     double cruiseTime = cruiseDistance / maxVelocity;
    //     double totalTime = accelerationTime * 2+ cruiseTime;

}
