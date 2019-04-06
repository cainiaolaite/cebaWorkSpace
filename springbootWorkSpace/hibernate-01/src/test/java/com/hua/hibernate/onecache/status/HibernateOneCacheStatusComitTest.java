package com.hua.hibernate.onecache.status;

import com.hua.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * hibernate连接测试
 */
public class HibernateOneCacheStatusComitTest {

    //SessionFactoryImpl(MetadataImplementor metadata, SessionFactoryOptions options)
    private SessionFactory sessionFactory=null;
    private static final String HIBERANTE_CONFIG_FILE="hibernate-configuration.xml";
    private Session session;
    private Transaction transaction;
    @Before
    public void before(){
        /**
         * 分析： 需要构建SessionFactory   SessionFactoryImpl(MetadataImplementor metadata, SessionFactoryOptions options)
         *          需要  Mapping(映射文件)  SessionFactoryOptions （参数）
         *
         *        SessionFactoryBuilderImpl  Session 工厂的建造者模式
         */
        //1.通过配置文件获取
        Configuration configuration=new Configuration().configure(HIBERANTE_CONFIG_FILE);//实例化它需要这个对象  默认的参数 hibernate.cfg.xml
        ServiceRegistry serviceRegistry=configuration.getStandardServiceRegistryBuilder().build();
        MetadataSources metadataSources=new MetadataSources(serviceRegistry);
        sessionFactory=metadataSources.buildMetadata().buildSessionFactory();
        session=sessionFactory.openSession();
        transaction=session.getTransaction();
        transaction.begin();
    }

    @Test
    public void saveUser(){
        User user=new User();
        user.setId("00"+System.currentTimeMillis());
        user.setUserName("hahha");
        user.setPassword("hahha");
        user.setEmail("1308189967@qq.com");
        user.setPhone("13476096121");
        user.setAddress("武汉");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //user对象被新建好后 状态为 临时状态，不被session托管
        session.save(user);
        //session.save(user)执行后 user对象被session缓存托管，即存在session缓存中，又存在与数据库中 被称为临时状态
        System.out.println("新增保存的user:"+user.toString());
        System.out.println("在user状态为持久化状态时，更新userName为【--wuhaihua--】");
        user.setUserName("--wuhaihua--");
        System.out.println("提交了");
        transaction.commit();
        System.out.println("如日志所示：\n user 在持久状态下，更新了userName ，提交了事务，数据库执行了 插入，更新两步操作" +
                "\n 说明提交时 commit 做了 flush 操作，只有fulsh操作才使数据库的数据 跟 缓存一致。");
    }


    @After
    public void after(){
        session.close();
        sessionFactory.close();
    }

}
