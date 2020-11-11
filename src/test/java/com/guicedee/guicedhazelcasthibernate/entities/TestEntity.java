package com.guicedee.guicedhazelcasthibernate.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class TestEntity
{
	@Id
	private long id;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
