/*
package CSUSoftWare21.web.projectJPetStore.persistence;

import domain.petShop.Account;
import domain.petShop.Category;
import domain.petShop.Itemm;
import domain.petShop.Sequence;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {

        //1.加载核心配置文件
        //定义配置文件路径
        String resource = "mybatis-config.xml";
        //获取InputStream
        //Resources是mybatis提供的资源加载类
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //通过SqlSessionFactoryBuilder对象的build方法把流传进来
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象,用它执行sql语句
        SqlSession sqlSession=sqlSessionFactory.openSession();
/*Mybatis入门
        //3.执行sql
        List<Account> accounts=sqlSession.selectList("test.selectAll");
*/
        /*mapper代理开发*/
        //获取accountmapper接口的代理对象
        /*AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        CategoryMapper categoryMapper=sqlSession.getMapper(CategoryMapper.class);
        ItemMapper itemMapper=sqlSession.getMapper(ItemMapper.class);
        SequenceMapper sequenceMapper=sqlSession.getMapper(SequenceMapper.class);
        //要执行的sql语句封装成方法，方法名是id（xml文件中）
        //List<Account> accounts=accountMapper.selectAll();
        *//*Account account = accountMapper.getAccountByUsername("ACID");
        System.out.println(account);*//*
        //Category cats = CategoryMapper();
        //System.out.println(cats);
        //List<Itemm> items = itemMapper.selectAllItem();
        //System.out.println(items);
        //Itemm item = itemMapper.getItem("EST-10");
        //System.out.println(item);
        Sequence sequence=new Sequence();
        sequence.setName("s");
        Sequence sequence1 = sequenceMapper.getSequence(sequence);
        System.out.println(sequence1);

        sqlSession.close();
    }
}*/