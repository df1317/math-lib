package org.team1317.mathLib;

public class FeedForward {
    protected final double ks;
    protected final double kv;
    protected final double ka;
    protected final double kg;

    public FeedForward(double ks, double kv, double ka) {
        this.ks = ks;
        this.kv = kv;
        this.ka = ka;
        this.kg = 0.0;
    }
    /**
     * If you need an elevator
     * @param ks
     * @param kv
     * @param ka
     * @param kg
     */
    public FeedForward(double ks, double kv, double ka, double kg) {
        this.ks = ks;
        this.kv = kv;
        this.ka = ka;
        this.kg = kg;
    }

    protected double calculateBase(double velocity, double acceleration, double kg){
        return (ks * Math.signum(velocity)) + (kv * velocity) + (ka * acceleration) + kg;
    }
    public double calculate(double velocity, double acceleration){
        return calculateBase(velocity, acceleration, kg);
    }
}
