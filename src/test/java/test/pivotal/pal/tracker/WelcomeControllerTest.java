package test.pivotal.pal.tracker;

import io.pivotal.pal.tracker.WelcomeController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WelcomeControllerTest {

    @Test
    public void itSaysHello() throws Exception {
        WelcomeController controller = new WelcomeController("A welcome message");

        Assertions.assertThat(controller.sayHello()).isEqualTo("A welcome message");
    }
}
