import com.guicedee.guicedhazelcasthibernate.TestDBPrivateModule;
import com.guicedee.guicedinjection.interfaces.IGuiceModule;

module guiced.hazelcast.hibernate.tests {
	
	requires com.guicedee.guicedhazelcast;
	
	
	requires org.junit.jupiter.api;
	//requires org.slf4j;
	//requires org.slf4j.simple;
	requires com.google.guice.extensions.persist;
	requires com.guicedee.guicedpersistence;
	
	provides IGuiceModule with TestDBPrivateModule;
	
	opens com.guicedee.guicedhazelcasthibernate to org.junit.platform.commons,com.google.guice;
	opens com.guicedee.guicedhazelcasthibernate.entities to org.junit.platform.commons, org.hibernate.orm.core,com.google.guice;
}