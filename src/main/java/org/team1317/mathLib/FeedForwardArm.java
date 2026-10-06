package org.team1317.mathLib;

public class FeedForwardArm extends FeedForward {
    public FeedForwardArm(double ks, double kv, double ka, double kg) {
        super(ks, kv, ka, kg);
    }
    public double calculate(double velocity, double acceleration, double angle) {
        return super.calculateBase(velocity, acceleration, super.kg * Math.cos(angle));
    }
}
