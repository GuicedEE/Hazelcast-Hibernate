package com.guicedee.guicedhazelcast.hibernate;

import com.guicedee.guicedhazelcast.HazelcastProperties;
import com.guicedee.guicedhazelcast.services.HazelcastClientPreStartup;
import com.guicedee.guicedpersistence.services.IPropertiesEntityManagerReader;
import lombok.extern.java.Log;
import org.hibernate.jpa.boot.internal.ParsedPersistenceXmlDescriptor;
import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import static com.guicedee.guicedhazelcast.HazelcastProperties.HazelcastNativeClientProperty;
import static com.guicedee.guicedhazelcast.services.HazelcastClientPreStartup.clientInstance;

@SuppressWarnings("unused")
@Log
public class HazelcastHibernateProperties
				implements IPropertiesEntityManagerReader
{
	
	@Override
	public Map<String, String> processProperties(PersistenceUnitDescriptor persistenceUnit, Properties incomingProperties)
	{
		Map<String, String> props = new HashMap<>();
		
		props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.internal.JCacheRegionFactory");
		props.put("hibernate.javax.cache.provider", "com.hazelcast.client.cache.impl.HazelcastClientCachingProvider");
		props.put("hibernate.cache.use_minimal_puts", "true");
		props.put("hibernate.cache.use_second_level_cache", "true");
		props.put("hibernate.cache.use_query_cache", "true");
		props.put("hibernate.cache.hazelcast.use_native_client", "true");
		props.put("hibrenate.cache.hazelcast.eviction.size", "1000");
		props.put("hibrenate.cache.hazelcast.time-to-live-seconds", "300");
		props.put("hibrenate.cache.hazelcast.max-idle-seconds", "500");

		if (!incomingProperties.containsKey("hibernate.cache.use_second_level_cache"))
		{
			props.put("hibernate.cache.use_second_level_cache", "true");
		}
		if (!incomingProperties.containsKey("hibernate.cache.use_query_cache"))
		{
			props.put("hibernate.cache.use_query_cache", "true");
		}
		if (!incomingProperties.containsKey("hibernate.cache.use_minimal_puts"))
		{
			props.put("hibernate.cache.use_minimal_puts", "true");
		}
		
		if (HazelcastProperties.address != null)
		{
			props.put(HazelcastNativeClientProperty, "true");
			props.put("hibernate.cache.hazelcast.native_client_hosts", HazelcastProperties.address);
			props.put("hibernate.cache.hazelcast.native_client_address", HazelcastProperties.address);
		}
		if (HazelcastProperties.groupName != null)
		{
			props.put(HazelcastNativeClientProperty, "true");
			props.put("hibernate.cache.hazelcast.native_client_group", HazelcastProperties.groupName);
			props.put("hibernate.cache.hazelcast.native_client_cluster", HazelcastProperties.groupName);
			props.put("hibernate.cache.hazelcast.native_client_cluster_name", HazelcastProperties.groupName);
		}
		
		if (HazelcastProperties.instanceName != null)
		{
			props.put(HazelcastNativeClientProperty, "true");
			props.put("hibernate.cache.hazelcast.instance_name", HazelcastProperties.getInstanceName());
		}
		
		if (HazelcastProperties.regionName != null)
		{
			props.put("hibernate.cache.region_prefix", HazelcastProperties.regionName);
			if (HazelcastProperties.isUseLocalRegionFactory())
			{
				props.put("hibernate.cache.region.factory_class", "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory");
			} else
			{
				props.put("hibernate.cache.region.factory_class", "com.hazelcast.hibernate.HazelcastCacheRegionFactory");
			}
		}
		
		if (HazelcastClientPreStartup.clientInstance != null)
		{
			props.put("hibernate.cache.hazelcast.instance_name", clientInstance.getName());
		}
		return props;
	}
	
	@Override
	public boolean applicable(PersistenceUnitDescriptor persistenceUnit)
	{
		return true;
	}
	
}
