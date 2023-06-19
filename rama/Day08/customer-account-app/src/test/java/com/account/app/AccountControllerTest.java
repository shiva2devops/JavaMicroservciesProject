package com.account.app;

import com.account.app.controller.AccountController;
import com.account.app.model.Account;
import com.account.app.service.RepositoryService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @InjectMocks
    AccountController accountController;
    @MockBean
    RepositoryService repositoryService;
    @BeforeEach
    void setUp(){
        repositoryService= Mockito.mock(RepositoryService.class);
        accountController=new AccountController(repositoryService);
    }
    @Test
    public void testGetAccount(){
        Account expectedAccount= new Account("123",20000,"account1");
        Mockito.when(repositoryService.getDetail(anyString())).thenReturn(expectedAccount);
        Account actualAccount=accountController.getAccountDetail("1234");
        Assert.assertEquals(actualAccount.getBalance(),expectedAccount.getBalance());
        Assert.assertEquals(actualAccount.getId(),expectedAccount.getId());
        Assert.assertEquals(actualAccount.getName(),expectedAccount.getName());
    }
    @Test
    public void testSaveAccount(){

        //Assert.assertEquals(result,11);
    }
    @Test
    public void testUpdateAccount(){

        //Assert.assertEquals(result,11);
    }
    @Test
    public void testDeleteAccount(){

       // Assert.assertEquals(result,11);
    }

}
