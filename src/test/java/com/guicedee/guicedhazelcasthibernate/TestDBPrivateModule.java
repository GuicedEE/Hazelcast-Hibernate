package com.guicedee.guicedhazelcasthibernate;

import com.guicedee.guicedpersistence.btm.BTMConnectionBaseInfo;
import com.guicedee.guicedpersistence.db.ConnectionBaseInfo;
import com.guicedee.guicedpersistence.db.DatabaseModule;
import org.hibernate.jpa.boot.internal.ParsedPersistenceXmlDescriptor;

import jakarta.validation.constraints.NotNull;
import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;

import java.lang.annotation.Annotation;
import java.util.Properties;

public class TestDBPrivateModule
		extends DatabaseModule<TestDBPrivateModule>
{

	@NotNull
	@Override
	protected String getPersistenceUnitName()
	{
		return "guiceinjectionh2test";
	}

	@Override
	protected @NotNull ConnectionBaseInfo getConnectionBaseInfo(PersistenceUnitDescriptor unit, Properties filteredProperties)
	{
		return new BTMConnectionBaseInfo();
	}

	@NotNull
	@Override
	protected String getJndiMapping()
	{
		return "jdbc/jndi";
	}
}
