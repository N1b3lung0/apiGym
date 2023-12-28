package n1b3lung0.apiGym.common;


import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.config.AppProfile;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
@UtilityClass
public final class TestContainersRunner {

    private static final String POSTGRES_IMAGE = "postgres:15-alpine";
    private static final String DBEAVER_IMAGE = "dbeaver/cloudbeaver";
    private static final String DB_HOST_NAME = "db";
    private static final int CLOUDBEAVER_PORT = 8978;

    public static void runContainers() {

        Network dbNetwork = Network.newNetwork();

        PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(POSTGRES_IMAGE)
                .withNetwork(dbNetwork)
                .withNetworkAliases(DB_HOST_NAME);
        postgres.start();

        GenericContainer<?> cloudBeaver = new GenericContainer<>(DBEAVER_IMAGE)
                .withExposedPorts(CLOUDBEAVER_PORT)
                .withNetwork(dbNetwork);
        cloudBeaver.start();

        log.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        log.info("PostgreSQL test database can be accessed via CloudBeaver in http://localhost:" + cloudBeaver.getMappedPort(CLOUDBEAVER_PORT) + " with HOST " + DB_HOST_NAME + ", DATABASE " + postgres.getDatabaseName() + ", USERNAME " + postgres.getUsername() + " and PASSWORD " + postgres.getPassword());
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.setProperty("spring.profiles.active", AppProfile.TEST);
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }
}
