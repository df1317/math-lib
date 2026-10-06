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
        double stoppingDistance = 
            ((Math.pow(currentVelocity, 2) - Math.pow(goalVelocity, 2)) / (2 * maxAcceleration));
        boolean startDeceleration = stoppingDistance >= Math.abs(goalPosition - currentPosition);
        System.out.println("stopping distance: "+stoppingDistance+" distance from goal: "+Math.abs(goalPosition - currentPosition));
        if(startDeceleration) {
            return  currentVelocity - (maxAcceleration * deltaSeconds);
        }
        double nextVelocity = currentVelocity + (maxAcceleration * deltaSeconds);
        nextVelocity = Math.clamp(nextVelocity, -maxVelocity, maxVelocity);

        return nextVelocity;
    }
}
