package com.guicedee.guicedhazelcasthibernate;

import com.google.inject.persist.UnitOfWork;
import com.guicedee.client.*;
import com.guicedee.guicedhazelcast.HazelcastProperties;
import com.guicedee.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import java.util.logging.Level;

public class GuicedPersistenceBindingTest
{
	@Test
	public void testMe() throws InterruptedException
	{
		HazelcastProperties.setStartLocal(true);
		System.setProperty("GROUP_NAME", "test");
		IGuiceContext.getContext().inject();
		UnitOfWork uw = IGuiceContext.get(UnitOfWork.class, TestCustomPersistenceLoader.class);
		EntityManager em = IGuiceContext.get(EntityManager.class, TestCustomPersistenceLoader.class);
		System.out.println("open : " + em.isOpen());
	}
}
