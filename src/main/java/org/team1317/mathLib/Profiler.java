package org.team1317.mathLib;

public interface Profiler {
    double calculate(double currentPosition, double currentVelocity, double deltaSeconds);
    void setGoal(double goalPosition, double goalVelocity);
}

