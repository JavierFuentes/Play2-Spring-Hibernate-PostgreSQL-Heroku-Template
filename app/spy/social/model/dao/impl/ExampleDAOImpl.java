package spy.social.model.dao.impl;

import common.model.dao.hibernate.spring.impl.GenericHibernateSpringDAOImpl;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import spy.social.model.dao.ExampleDAO;
import spy.social.model.vo.Example;

import java.util.List;

/**
 * Created by javierfuentes on 24/08/14.
 */
public class ExampleDAOImpl
        extends GenericHibernateSpringDAOImpl<Example, Long>
        implements ExampleDAO {

    public ExampleDAOImpl() {
        super(Example.class);
    }

    @Override
    public List<Example> getExampleList() {
        try {

            Criterion nameCriteria = null;

            nameCriteria = Restrictions.like("name", "%");

            Order idOrder = Order.asc("name");

            List<Example> returnValue = super.findByCriteria(null, idOrder, nameCriteria);

            return returnValue;

        } catch (Exception e) {

            logger.error("getExampleList - " + e.toString());
            return null;

        }
    }
}
