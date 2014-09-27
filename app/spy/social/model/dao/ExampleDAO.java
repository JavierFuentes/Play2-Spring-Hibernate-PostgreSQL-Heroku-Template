package spy.social.model.dao;

import common.model.dao.hibernate.GenericHibernateDAO;
import spy.social.model.vo.Example;

import java.util.List;

/**
 * Created by javierfuentes on 16/08/14.
 */
public interface ExampleDAO extends GenericHibernateDAO<Example, Long> {

    public List<Example> getExampleList();

}
