package lib;
// Note: Set of basic utilities that adds some basic functionality and are shared across
// testing environment

public class SharedUtil {
    private String logging;

    public SharedUtil() {
        // note: right now anything other then MINIMAL loggs
        // can be extended to other things like VERBOSE or maybe implement
        // a logger in the future
        logging = System.getenv("LOGGING");
        if (logging == null) {
            logging = "VERBOSE";
            System.out.println("Environmental Variable LOGGING Not Set - using " + logging);
        }
    }

    public void log(String logText) {
        if (!logging.contains("MINIMAL")) { System.out.println(logText); }
    }

	// heavy handed way of pausing, would rather use fluid waits
	// but sometimes needed to synchronize with animations where we don't
	// get a good end event.
    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            System.out.println("Something went wrong when pausing");
        }
    }

	// quick way to pause while debugging to let us see what is going on with the screen
    public void pause() {
        this.pause(10000);
    }

}
