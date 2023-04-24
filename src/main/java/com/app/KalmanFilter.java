package com.app;

public class KalmanFilter {
        private double x; // Предсказанное значение
        private double P; // Ковариационная матрица ошибок

        private double Q; // Шум процесса
        private double R; // Шум измерений

        public void init(double x0, double P0, double Q, double R) {
            this.x = x0;
            this.P = P0;
            this.Q = Q;
            this.R = R;
        }

        public double update(double z, double dt) {
            // Определение коэффициентов Калмана
            double K = P / (P + R);

            // Корректировка предсказанного значения
            x += K * (z - x);

            // Корректировка ковариационной матрицы ошибок
            P = (1 - K) * P + Q * dt;

            return x;
        }
    }




