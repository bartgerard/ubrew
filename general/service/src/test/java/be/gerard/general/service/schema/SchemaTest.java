package be.gerard.general.service.schema;

import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author bartgerard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:be.gerard.general.service.test.xml")
@ActiveProfiles("hib")
public class SchemaTest {
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Test
    public void testCreateSchema() {
        sessionFactory.getConfiguration().setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        sessionFactory.getConfiguration().setProperty(Environment.HBM2DDL_AUTO, "create");
        SchemaExport export = new SchemaExport(sessionFactory.getConfiguration());
        export.setDelimiter(";");
        export.setOutputFile("./target/general_ddl_mysql.sql");
        export.execute(true, false, false, false);
    }

}