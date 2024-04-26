package com.guicedee.guicedhazelcasthibernate;

import com.google.inject.persist.UnitOfWork;
import com.guicedee.client.*;
import com.guicedee.guicedhazelcast.HazelcastProperties;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;

public class GuicedPersistenceBindingTest
{
	@Test
	public void testMe() throws InterruptedException
	{
		HazelcastProperties.setStartLocal(true);
		System.setProperty("GROUP_NAME", "test");
		IGuiceContext.getContext().inject();
		UnitOfWork uw = IGuiceContext.get(UnitOfWork.class);
		EntityManager em = IGuiceContext.get(EntityManager.class);
		System.out.println("open : " + em.isOpen());
	}
}
