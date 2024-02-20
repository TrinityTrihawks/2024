package frc.robot;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class DynamicSlewer {
    private SlewRateLimiter internalLimiter;
    private double previousValue = 0;

    public DynamicSlewer(double initialRateLimit) {
        internalLimiter = new SlewRateLimiter(initialRateLimit);
    }

    public double calculate(double x) {
        previousValue = x;
        return internalLimiter.calculate(x);
    }

    public void reset(double x) {
        previousValue = x;
        internalLimiter.reset(x);
    }

    public void updateRateLimit(double dx) {
        internalLimiter = new SlewRateLimiter(dx);
        internalLimiter.reset(previousValue);
    }

    public double getPreviousValue() {
        return previousValue;
    }
}
