package spy.social.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spy.social.model.dao.ExampleDAO;
import spy.social.model.vo.Example;

import java.util.List;

/**
 * Created by javierfuenteserfuentes on 16/08/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context.xml")
@Transactional
public class ExampleTest
        extends AbstractTransactionalJUnit4SpringContextTests {

    protected final String NAME1 = "Javier";
    protected final String NAME2 = "Jorge";
    protected final String NAME3 = "Paco";
    protected final String NAME4 = "Luis";
    protected final String NAME5 = "Consuelo";

    protected Example example1, example2, example3, example4, example5;

    @Autowired
    @Qualifier(value = "target")
    protected ExampleDAO exampleDAO;

    @Before
    public void init() {
        // Insert new Examples
        example1 = new Example();
        example1.setName(NAME1);
        exampleDAO.makePersistent(example1);

        example2 = new Example();
        example2.setName(NAME2);
        exampleDAO.makePersistent(example2);

        example3 = new Example();
        example3.setName(NAME3);
        exampleDAO.makePersistent(example3);

        example4 = new Example();
        example4.setName(NAME4);
        exampleDAO.makePersistent(example4);

        example5 = new Example();
        example5.setName(NAME5);
        exampleDAO.makePersistent(example5);
    }

    @Test
    public void getExamples() {
        // Get the Examples from DB
        Example exampleCopy1 = exampleDAO.getById(example1.getId(), false);
        Example exampleCopy2 = exampleDAO.getById(example2.getId(), false);
        Example exampleCopy3 = exampleDAO.getById(example3.getId(), false);
        Example exampleCopy4 = exampleDAO.getById(example4.getId(), false);
        Example exampleCopy5 = exampleDAO.getById(example5.getId(), false);

        // Verify
        Assert.assertEquals(example1.getName(), exampleCopy1.getName());
        Assert.assertEquals(example2.getName(), exampleCopy2.getName());
        Assert.assertEquals(example3.getName(), exampleCopy3.getName());
        Assert.assertEquals(example4.getName(), exampleCopy4.getName());
        Assert.assertEquals(example5.getName(), exampleCopy5.getName());
    }

    @Test
    public void updateExamples() {
        // Modify the Examples and save
        example1.setName("Javi");
        exampleDAO.makePersistent(example1);

        // Get the Example from DB
        Example exampleCopy1 = exampleDAO.getById(example1.getId(), false);

        // Verify
        Assert.assertEquals(example1.getName(), exampleCopy1.getName());
    }

    @Test
    public void deleteExample() {
        // Delete the Example from DB
        exampleDAO.makeTransient(example1);

        // Get the Example from DB
        Example exampleCopy1 = exampleDAO.getById(example1.getId(), false);

        // Verify
        Assert.assertEquals(null, exampleCopy1);
    }

    @Test
    public void getAllExamples() {
        // Get All Examples
        List<Example> exampleList = exampleDAO.getExampleList();

        // Verify. Ordered Lists
        Assert.assertEquals(true, exampleList.size() >= 5);

        Assert.assertEquals(NAME5, exampleList.get(0).getName());
    }

}
