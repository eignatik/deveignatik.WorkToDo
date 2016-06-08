package Data;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Eugen on 6/8/2016.
 */
public class Log {
    public static final Logger log = LogManager.getLogger("deveignatik.WorkToDo");
    public static void loggerTest(){
        log.error("log...");
    }
}
