package lib;

public class SerialTestFixture extends SerialArduino {

    ShellCommand cmd; // can also execute shell commands
    public String type;

    public void initialize() {

        util.log("TestFixture initialize called");
        cmd = new ShellCommand();
        openSerialPort();
    }

    public void cleanup() {
        closeSerialPort();
    }

    public boolean powerOn() {
        util.log("Power On called");
        return setPowerRelayOn();
    }

    public boolean powerOff() {
        util.log("Power Off called");
        return setPowerRelayOff();
    }

    public boolean setupPython() {
        util.log ("SetupPython started");
        int results = cmd.pythonExe("~", "Help");
        util.log("SetupPython completed");
        return (results == 0);
    }
}
