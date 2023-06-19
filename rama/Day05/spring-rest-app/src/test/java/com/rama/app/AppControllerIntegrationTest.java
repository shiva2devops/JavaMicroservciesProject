package com.rama.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest({AppController.class})
@ActiveProfiles(value = "test")
public class AppControllerIntegrationTest {
    @InjectMocks
    AppController appController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    MathOperation mathOperation;

   @Test
    public void testgetdataController() throws Exception {
       String data="test";
       String expectedResult= "My data is: " + data;
       ResultActions resultActions= mockMvc.perform(get("/getdata").param("data",data));
       resultActions.andExpect(status().isOk());
       String result= resultActions.andReturn().getResponse().getContentAsString();
       Assert.assertEquals(result,expectedResult);
       Assert.assertEquals(resultActions.andReturn().getResponse().getStatus(),200);
   }

    @Test
    public void testgatDataVariableController() throws Exception {
        String data="mydata";
        String expectedResult= "Variable data is: " + data;
        ResultActions resultActions= mockMvc.perform(get("/getdata/mydata"));
        resultActions.andExpect(status().isOk());
        String result= resultActions.andReturn().getResponse().getContentAsString();
        Assert.assertEquals(result,expectedResult);
        Assert.assertEquals(resultActions.andReturn().getResponse().getStatus(),200);
    }

    @Test
    public void testsubmitUserdataController() throws Exception {
        UserData userData=new UserData("myname","23", "delhi",  "862435364",  "23000",  "2345678");
        ResultActions resultActions= mockMvc.perform(post("/submit/userdata").
                contentType(MediaType.APPLICATION_JSON)
                .content(asObjectToJsonString(userData)).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        String result= resultActions.andReturn().getResponse().getContentAsString();
        UserData userData1= asJsonStringToObject(result);
        Assert.assertEquals(resultActions.andReturn().getResponse().getStatus(),200);
        Assert.assertEquals(userData1.getAccount(),userData.getAccount());
        Assert.assertEquals(userData1.getAge(),userData.getAge());
        Assert.assertEquals(userData1.getName(),userData.getName());
        Assert.assertEquals(userData1.getAddress(),userData.getAddress());
    }


    @Test
    public void testaddValueController() throws Exception {
        String str1="first";
        String str2="second";
        String expectedResult= "firstsecond";
        Mockito.when(mathOperation.addString(anyString(),anyString())).thenReturn(expectedResult);
        ResultActions resultActions= mockMvc.perform(get("/addString").
                param("str1",str1).
                param("str2",str2));
        resultActions.andExpect(status().isOk());
        String result= resultActions.andReturn().getResponse().getContentAsString();
        Assert.assertEquals(result,expectedResult);
        Assert.assertEquals(resultActions.andReturn().getResponse().getStatus(),200);
    }

    private String asObjectToJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserData asJsonStringToObject( String result) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            UserData userData= mapper.readValue(result, UserData.class);
            return userData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
