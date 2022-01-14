package btlhttt.database;

import btlhttt.models.ComputerError;
import btlhttt.repositories.ComputerErrorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ComputerErrorRepository computerErrorRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                ComputerError e1 = new ComputerError("hong card", "thay card");
//                ComputerError e2 = new ComputerError("loi o cung", "thay o cung");
//                logger.info("insert data: " + computerErrorRepository.save(e1));
//                logger.info("insert data: " + computerErrorRepository.save(e2));
            }
        };
    }
}
