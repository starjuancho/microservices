package com.globant.pruebas.profileservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@ConfigurationProperties(prefix="cassandra")
@EnableCassandraRepositories(basePackages="com.globant.pruebas.profileservice.dao.cassandra")
public class CassandraConfig extends AbstractCassandraConfiguration {

	private String host;
	private int port;
	
	@Override
	protected String getKeyspaceName() {
		return "testKeySpace";
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(host);
		cluster.setPort(port);
		return cluster;
	}

	@Bean
	public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
		return new CassandraMappingContext();
	}

}
