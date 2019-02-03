package io.github.mosser.arduinoml.kernel.structural;

public class AnalogSensor extends Sensor {

    private Integer threshold;

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}
