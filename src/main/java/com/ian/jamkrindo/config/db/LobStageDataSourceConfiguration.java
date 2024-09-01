package com.ian.jamkrindo.config.db;


import com.ian.jamkrindo.model.entity.stage.LobStage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages ="com.ian.jamkrindo.repository.stage",
        entityManagerFactoryRef = "lobStageEntityManagerFactory",
        transactionManagerRef = "lobStageTransactionManager"
)
public class LobStageDataSourceConfiguration {
    @Bean()
    @ConfigurationProperties(prefix = "integration.datasource.db-mis-stage")
    public DataSource lobStageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "lobStageEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean lobStageEntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(lobStageDataSource())
                .packages(LobStage.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager lobStageTransactionManager(
            @Qualifier("lobStageEntityManagerFactory") LocalContainerEntityManagerFactoryBean lobStageEntityManagerFactory
    ) {
        return new JpaTransactionManager(lobStageEntityManagerFactory.getObject());
    }
}
