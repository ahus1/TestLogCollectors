package test;

import org.junit.Rule;
import org.junit.Test;

import java.util.logging.Logger;

import dk.bitcraft.lc.JUnit4LogCollector;

import static org.assertj.core.api.Assertions.assertThat;

// http://docs.oracle.com/javase/8/docs/technotes/guides/logging/overview.html
public class RuleTest {

    private static final Logger logger = Logger.getLogger("test.logger");

    @Rule
    public JUnit4LogCollector logCollector = new JUnit4LogCollector(logger);

    @Test
    public void test() {
        logger.warning("This is a warning!");
        logger.warning("This is another warning!");

        assertThat(logCollector.getLogs()).hasSize(2);
    }

    @Test
    public void verifyAdditivity() {
        {
            Logger logger = Logger.getLogger("test.logger.subLogger");
            logger.warning("This should be available in the collector");
        }

        assertThat(logCollector.getLogs()).hasSize(1);
    }
}

