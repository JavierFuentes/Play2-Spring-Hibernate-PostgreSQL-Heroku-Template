package spy.social.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import play.mvc.Controller;
import play.mvc.Result;
import spy.social.model.dao.ExampleDAO;
import spy.social.model.vo.Example;

import java.util.List;

//import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * Created by javierfuentes on 16/08/14.
 */
@org.springframework.stereotype.Controller
public class ExampleServices
        extends Controller {

    @Autowired
    @Qualifier(value = "target")
    protected ExampleDAO exampleDAO;

    public Result getExampleList() throws JsonProcessingException {
        List<Example> exampleList = exampleDAO.getExampleList();

        ObjectMapper mapper = new ObjectMapper();
        return ok(mapper.writeValueAsString(exampleList));
    }

    public Result getExampleDetail(Long id) throws JsonProcessingException {
        Example example = exampleDAO.getById(id, false);

        ObjectMapper mapper = new ObjectMapper();
        return ok(mapper.writeValueAsString(example));
    }

    public Result addExample() throws JsonProcessingException {
        JsonNode json = request().body().asJson();
        String name = json.findPath("data_name").asText();

        Example example = new Example();
        example.setName(name);
        example = exampleDAO.makePersistent(example);  // auto-incremented PK

        ObjectMapper mapper = new ObjectMapper();
        return ok(mapper.writeValueAsString(example));
    }

    public Result deleteAllExamples() throws JsonProcessingException {
        List<Example> exampleList = exampleDAO.findAll();

        for(Example ex : exampleList) {
            exampleDAO.makeTransient(ex);
        }

        ObjectMapper mapper = new ObjectMapper();
        return ok(mapper.writeValueAsString("No Data"));
    }
}
