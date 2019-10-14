package lib;
// Modified from https://www.baeldung.com/run-shell-command-in-java

import java.io.*;
import java.util.concurrent.Executors;

public class ShellCommand {
    private SharedUtil util;

    public ShellCommand() {
        if (util == null) {
            util = new SharedUtil();
        }
    }

    public int execute(String path, String command, String param) {
        ProcessBuilder builder = new ProcessBuilder();

        builder.directory(new File(path));
        builder.command(command, param);
        int exitCode;

        try {
            Process process = builder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            exitCode = process.waitFor(); // 0 = success
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to run " + command + " from the Shell");
            System.out.println("at path: " + path + "with param: " + param);
            exitCode = 1;
        }
        return exitCode;
    }

    public int execute(String command, String param) {
        return execute("/Users/shared/code/e2e-boiler-plate-tests", command, param);
    }

	// As long as python is installed on the system, we can run python files
    public int pythonExe(String path, String pythonFile) {
        return execute(path, "python", pythonFile);
    }

    public int pythonExec(String pythonFile) {
        return pythonExe("/Users/shared/code/e2e-boiler-plate-tests/python", pythonFile);
    }

    public int rolldice() {
        util.log("---\nrolldice says:");
        int exitCode = this.pythonExec("rolldice.py");
        util.log("---");
        return exitCode; // 0 = success
    }

    public int testTheDevice() {
        return this.pythonExec("TestTheDevice.py"); // 0 = success
    }

    public int upgradeFWOnTargetDevice() {
        String pyPath = "/code/e2e-boiler-plate-tests/python";
        String upgrade_binary = "/Users/Shared/code/e2e-boiler-plate-tests/apps/latestFW.bin";
        String jLinkDevice = "/Users/Shared/code/e2e-boiler-plate-tests/util/jlinkFlash.sh";
        util.log("---\nUpgrading target to latest FW");

        ProcessBuilder builder = new ProcessBuilder();

        builder.directory(new File(pyPath));
        builder.command("python", "flash_latest.py", upgrade_binary, jLinkDevice);
        int exitCode;

        try {
            Process process = builder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            exitCode = process.waitFor(); // 0 = success
        } catch (Exception e) {
            System.out.println("Something went wrong when upgrading to latest");
            exitCode = 1;
        }
        util.log("\nFW upgradeed to latest\n---");
        return exitCode;
    }

    // curl is handy to setup db to standard state as well as retrieve results
    public int curl(String path, String curlParams) {
        return execute(path, "curl", curlParams);
    }

    public int curl(String curlParams) {
        return curl("/Users/shared/code/e2e-boiler-plate-tests/postman", curlParams);
    }

    // postman is the best way to execute a series of End Point commands to test them as well as do setup to put
    // testing environment into a known state.
    // curl is handy to setup db to standard state as well as retrieve results
    public int postman(String path, String curlParams) {
        return execute(path, "newman", curlParams);
    }

    public int postman(String curlParams) {
        return postman("/Users/shared/code/e2e-boiler-plate-tests/postman/", curlParams);
    }
}
