package com.example.Lab7Task3;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometerSensor;
    Sensor gyroscopeSensor;
    Sensor magnetometerSensor;

    TextView xAxisAccelerometerTextView;
    TextView yAxisAccelerometerTextView;
    TextView zAxisAccelerometerTextView;

    TextView xAxisGyroscopeTextView;
    TextView yAxisGyroscopeTextView;
    TextView zAxisGyroscopeTextView;

    TextView xAxisMagnetometerTextView;
    TextView yAxisMagnetometerTextView;
    TextView zAxisMagnetometerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        xAxisAccelerometerTextView = findViewById(R.id.xAxisAccelerometerTextView);
        yAxisAccelerometerTextView = findViewById(R.id.yAxisAccelerometerTextView);
        zAxisAccelerometerTextView = findViewById(R.id.zAxisAccelerometerTextView);

        xAxisGyroscopeTextView = findViewById(R.id.xAxisGyroscopeTextView);
        yAxisGyroscopeTextView = findViewById(R.id.yAxisGyroscopeTextView);
        zAxisGyroscopeTextView = findViewById(R.id.zAxisGyroscopeTextView);

        xAxisMagnetometerTextView = findViewById(R.id.xAxisMagnetometerTextView);
        yAxisMagnetometerTextView = findViewById(R.id.yAxisMagnetometerTextView);
        zAxisMagnetometerTextView = findViewById(R.id.zAxisMagnetometerTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                displayAccelerometerValues(event.values);
                break;
            case Sensor.TYPE_GYROSCOPE:
                displayGyroscopeValues(event.values);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                displayMagnetometerValues(event.values);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No need to implement anything here
    }

    private void displayAccelerometerValues(float[] values) {
        xAxisAccelerometerTextView.setText("Accelerometer X-axis: " + String.format("%.2f", values[0]));
        yAxisAccelerometerTextView.setText("Accelerometer Y-axis: " + String.format("%.2f", values[1]));
        zAxisAccelerometerTextView.setText("Accelerometer Z-axis: " + String.format("%.2f", values[2]));
    }

    private void displayGyroscopeValues(float[] values) {
        xAxisGyroscopeTextView.setText("Gyroscope X-axis: " + String.format("%.2f", values[0]));
        yAxisGyroscopeTextView.setText("Gyroscope Y-axis: " + String.format("%.2f", values[1]));
        zAxisGyroscopeTextView.setText("Gyroscope Z-axis: " + String.format("%.2f", values[2]));
    }

    private void displayMagnetometerValues(float[] values) {
        xAxisMagnetometerTextView.setText("Magnetometer X-axis: " + String.format("%.2f", values[0]));
        yAxisMagnetometerTextView.setText("Magnetometer Y-axis: " + String.format("%.2f", values[1]));
        zAxisMagnetometerTextView.setText("Magnetometer Z-axis: " + String.format("%.2f", values[2]));
    }
}
