package org.team1317.mathLib;

public class PID {
    private double kp;
    private double ki;
    private double kd;

    private double integralWindUp;

    private double integral = 0;
    private double previousError = 0;

    public PID(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.integralWindUp = -1;
    }
    public PID(double kp, double ki, double kd, double integralWindUp) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.integralWindUp = integralWindUp;
    }

    public double calculate(double setpoint, double current, double deltaSeconds){
        double error = setpoint - current;

        integral += error;
        if(integralWindUp > 0) integral = Math.clamp(integral, -integralWindUp, integralWindUp);

        double out = (kp * error) + (ki * integral) + (kd * ((error - previousError) / deltaSeconds));

        previousError = error;
        return out;
    }
}
