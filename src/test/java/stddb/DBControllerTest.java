package stddb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
public class DBControllerTest {

    @Autowired
    MockMvc mvc;


    @Test
    void homeTest() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders.get("/");
        MvcResult result=mvc.perform(request)
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("hello from")))
                        .andReturn();
    }

    @Test
    void insertTest() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders
                .post("/insert")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":7,\"first_name\":\"Rintu\",\"last_name\":\"Karmakar\",\"age\":18,\"phone_no\":\"9732078818\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result=mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved")))
                .andReturn();
    }

    @Test
    void findAllTest() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders
                .get("/findAll")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result=mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void findByIdTest() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders
                .get("/find/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result=mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1,first_name:'Prasenjit',last_name:'Karmakar',age:22,phone_no:'6296331375'}"))
                .andReturn();

        request= MockMvcRequestBuilders
                .get("/find/100")
                .accept(MediaType.APPLICATION_JSON);

        result=mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("not found")))
                .andReturn();
    }

    @Test
    void updateTest() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders
                .put("/update/6")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":6,\"first_name\":\"Mintu\",\"last_name\":\"Karmakar\",\"age\":18,\"phone_no\":\"9732078818\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result=mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("updated")))
                .andReturn();

        request= MockMvcRequestBuilders
                .put("/update/100")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":100,\"first_name\":\"Pintu\",\"last_name\":\"Karmakar\",\"age\":18,\"phone_no\":\"9732078818\"}")
                .contentType(MediaType.APPLICATION_JSON);

        result=mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("doesn't exist")))
                .andReturn();
    }

    @Test
    void deleteTest() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders
                .delete("/delete/6")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result=mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("deleted")))
                .andReturn();

        request= MockMvcRequestBuilders
                .delete("/delete/100")
                .accept(MediaType.APPLICATION_JSON);

        result=mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("doesn't exist")))
                .andReturn();
    }


}
