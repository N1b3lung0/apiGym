package n1b3lung0.apiGym.common;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Rollback
@Transactional
@SpringBootTest
public abstract class BaseIntegrationTest {

    @Autowired
    protected EntityManager em;

    static {
        if (isNotRunningInPipeline()) {
            TestContainersRunner.runContainers();
        }
    }

    private static boolean isNotRunningInPipeline() {
        return StringUtils.isBlank(System.getProperty("runner"))
                && StringUtils.isBlank(System.getProperty("spring.datasource.url"));
    }
}
