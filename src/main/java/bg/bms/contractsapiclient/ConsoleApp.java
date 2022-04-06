package bg.bms.contractsapiclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleApp implements CommandLineRunner {
    private final Runnable engine;

    public ConsoleApp(Runnable engine) {
        this.engine = engine;
    }

    @Override
    public void run(String... args) throws Exception {
        this.engine.run();
    }
}
