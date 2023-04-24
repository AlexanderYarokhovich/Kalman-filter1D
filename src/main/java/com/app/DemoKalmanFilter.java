package com.app;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class DemoKalmanFilter {
        public static void main(String[] args) {
// Инициализация генератора случайных чисел
            Random random = new Random();

// Создание массива измерений с шумом
            Measurement[] measurements = new Measurement[100];
            double value = 0;
            for (int i = 0; i < measurements.length; i++) {
                double noise = random.nextGaussian() * 10; // добавление шума к измерениям
                value += 1;
                double time = i * 1;
                measurements[i] = new Measurement(time, value + noise);
            }


            // Создание фильтра Калмана
            KalmanFilter filter = new KalmanFilter();
            filter.init(measurements[0].getValue(), 10, 1, 10);

            ArrayList<Double> filt = new ArrayList<>();
            filt.add(measurements[0].getValue());

            // Обработка измерений
            for (int i = 1; i < measurements.length; i++) {
                double time = measurements[i].getTime();
                double dt = i == 1 ? 1 : time - measurements[i-1].getTime();
                double filteredValue = filter.update(measurements[i].getValue(), dt);
                filt.add(filteredValue);
                System.out.println("Measurement: " + measurements[i].getValue() + ", Filtered: " + filteredValue);
            }
            // Создание коллекции данных для графика
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries measurementSeries = new XYSeries("Measurement");
            XYSeries filteredSeries = new XYSeries("Filtered");
            dataset.addSeries(measurementSeries);
            dataset.addSeries(filteredSeries);

// Создание графика
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Kalman Filter Demo",
                    "Time",
                    "Value",
                    dataset
            );

// Настройка осей графика
            XYPlot plot = chart.getXYPlot();
            NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
            domainAxis.setAutoRangeIncludesZero(false);
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setAutoRangeIncludesZero(false);

// Создание панели для графика
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            JFrame frame = new JFrame("Kalman Filter Demo");
            frame.setContentPane(chartPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

// Обработка измерений и добавление их на график
            for (int i = 0; i < measurements.length; i++) {
                double time = measurements[i].getTime();
                measurementSeries.add(time, measurements[i].getValue());
                filteredSeries.add(time, filt.get(i));
            }
        }
    }
