package com.app;

public class KalmanFilter {
        private double x; // predicted value
        private double P; // Error Covariance Matrix

        private double Q; // Process noise
        private double R; // Measurement noise

        public void init(double x0, double P0, double Q, double R) {
            this.x = x0;
            this.P = P0;
            this.Q = Q;
            this.R = R;
        }

        public double update(double z, double dt) {
            // Definition of Kalman coefficients
            double K = P / (P + R);

            // Predicted Value Adjustment
            x += K * (z - x);

            // Error Covariance Matrix Adjustment
            P = (1 - K) * P + Q * dt;

            return x;
        }
    }




