package io.test.payin.data.migration;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.util.ExceptionUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MigrationService {

    @Inject
    Logger logger;
    @ConfigProperty(name = "quarkus.liquibase.migrate-at-start")
    boolean runMigration;
    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String datasourceUrl;
    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;
    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;
    @ConfigProperty(name = "quarkus.liquibase.change-log")
    String changeLogLocation;

    @ConfigProperty(name = "quarkus.profile")
    String quarkusProfile;

    public void runLiquibaseMigration(@Observes StartupEvent event) throws LiquibaseException {
        if (runMigration) {
            Liquibase liquibase = null;
            try {
                ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader());
                DatabaseConnection conn = DatabaseFactory.getInstance().openConnection(datasourceUrl, datasourceUsername, datasourcePassword, null, resourceAccessor);
                liquibase = new Liquibase(changeLogLocation, resourceAccessor, conn);
                liquibase.update(new Contexts(quarkusProfile), new LabelExpression());
                logger.info(datasourceUrl);
            } catch (Exception e) {
                logger.error("Liquibase Migration Exception: " + ExceptionUtil.generateStackTrace(e));
            } finally {
                if (liquibase != null) {
                    liquibase.close();
                }
            }
        }
    }
}