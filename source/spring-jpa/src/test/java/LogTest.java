import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Test
    @DisplayName("LOG 테스트")
    void test1() {
        logger.info("info!!!!!");
        logger.debug("debug");
        logger.warn("warn@@@@");
        logger.trace("trace!!!");
    }
}
