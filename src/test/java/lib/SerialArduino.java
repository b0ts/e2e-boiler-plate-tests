package lib;
// Serial Arduino controls an Arduino Test Fixture by sending serial commands via Bluetooth
// The Bluetooth Arduino needs to be previously paired to the controlling computer.
// The Bluetooth Arduino needs to have firmware that supports a serial interface and 
// polling for sensors and commands for controlling things such as relays, servos, steppers, etc.
// See the Arduino folder for a firmware example.

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;

public class SerialArduino {
    SerialPort sp;
    private String commPort;
    private int delay;
    protected SharedUtil util;

    public SerialArduino() {
        this.commPort = "/dev/cu.OSEPPBT-BluetoothSerial";
        this.delay = 250;
        if (util == null) {
            util = new SharedUtil();
        }
    }

    public void openSerialPort() {
        util.log("Opening Serial Port to Arduino");
        this.sp = SerialPort.getCommPort(this.commPort); // device name
        this.sp.setComPortParameters(115200, 8, 1, 0); // default connection settings for Arduino
        this.sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (this.sp.openPort()) {
            util.log("Arduino Serial Port: AOK");
        } else {
            util.log("Arduino Serail Port: Failure");
        }
    }

    public void closeSerialPort() {
        util.log("Closing Arduino Serial Port");
        if (this.sp.closePort()) {
            util.log("Arduino Serial Port Close: AOK");
        } else {
            util.log("Arduino Serial Port Cose: FAILURE");
            return;
        }
    }

    public void sendCharacter(char output) {
        try {
            sp.getOutputStream().write(output);
            sp.getOutputStream().flush();
        } catch (Exception e) {
            util.log("Unable to send character ");
        }
        util.log("Single Character: " + output + " sent");
        util.pause(1000);
    }

    public String readFromSerial() {
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        InputStream in = sp.getInputStream();
        StringBuilder sb = new StringBuilder();
        char temp = ' ';
        try
        {
            while (temp != '\n') {
                temp = ((char) in.read());
                sb.append(temp);
            }
            in.close();
        } catch (Exception e) {
            String logging = System.getenv("LOGGING");
            if (logging == null) {
                logging = "MINIMAL";
            }
            if (!logging.contains("MINIMAL")) {
                e.printStackTrace();
            }
        }

        String results = sb.toString();
        util.log("Serial Port Returned: " + results);
        return results;
    }

    private boolean setRelay(char sendChar, String expectedResults) {
        sendCharacter(sendChar);
        util.pause(this.delay);
        String results = readFromSerial();
        util.pause(this.delay);
        if (!results.contains(expectedResults)) {
            util.log("Setting Relay " + sendChar + " failed");
            util.log("actualResults: " + results);
            util.log("expectedResults: " + expectedResults);
            return false;
        }
        return true;
    }

    private boolean setRelayWithRetry(char sendChar, String expectedResults, int pauseLength) {
        boolean results = setRelay(sendChar, expectedResults);
        if (!results) {
            util.log("settingRelay again");
            results = setRelay(sendChar, expectedResults);
        }
        util.pause(pauseLength);
        return results;
    }

    private boolean setRelayWithRetry(char sendChar, String expectedResults) {
        return setRelayWithRetry(sendChar, expectedResults, 1000);
    }

    public boolean setPowerRelayOn() { return setRelayWithRetry('p', "p=true" ); }

    public boolean setPowerRelayOff() { return setRelayWithRetry('p', "p=false" ); }

}
