package com.account.app;

import com.account.app.controller.AccountController;
import com.account.app.model.Account;
import com.account.app.service.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Base64Util;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Base64Utils;
import org.springframework.http.HttpHeaders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest({AccountController.class})
@ActiveProfiles(value = "test")
public class AccountControllerIntegrationTest {
    @InjectMocks
    AccountController appController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    RepositoryService repositoryService;
    @Test
    public void testgetAccountController() throws Exception {
        String secret= "Basic " + Base64Utils.encodeToString(("user1"+":"+"password").getBytes());
        Account expectedAccount= new Account("123",20000,"account1");
        Mockito.when(repositoryService.getDetail(anyString())).thenReturn(expectedAccount);
        ResultActions resultActions= mockMvc.perform(get("/get/account").header(HttpHeaders.AUTHORIZATION,secret).param("account_id","123"));
        resultActions.andExpect(status().isOk());
        String result= resultActions.andReturn().getResponse().getContentAsString();
        Account actualAccount= asJsonStringToObject(result);
        Assert.assertEquals(actualAccount.getName(),expectedAccount.getName());
        Assert.assertEquals(actualAccount.getId(),expectedAccount.getId());
        Assert.assertEquals(actualAccount.getBalance(),expectedAccount.getBalance());
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

    private Account asJsonStringToObject( String result) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            Account patient= mapper.readValue(result, Account.class);
            return patient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
