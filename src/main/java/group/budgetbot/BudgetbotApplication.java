package group.budgetbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BudgetbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetbotApplication.class, args);
    }

}
