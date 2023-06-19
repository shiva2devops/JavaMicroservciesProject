package com.rama.app;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class AppcontrollerTest {
    @InjectMocks
    AppController appController;
    @MockBean
    MathOperation mathOperation;
    @BeforeEach
    void setUp(){
        mathOperation= Mockito.mock(MathOperation.class);
        appController=new AppController(mathOperation);
    }

    @Test
    public void getAppTest(){
        String data="test";
        String expectedResult="My data is: " + data;
        String result=appController.getApp(data);
        Assert.assertEquals(expectedResult,result);
    }
    @Test
    public void gatDataVariableTest(){
        String data="tesdata";
        String expextedResult= "Variable data is: " + data;
        String result= appController.gatDataVariable(data);
        Assert.assertEquals(expextedResult,result);

    }
    @Test
    public void submitFormTest(){
        String name="myname";
        String age="23";
        String address="myaddress";

      String expectedResult= "Variable data is name: " + name + " age:" + age + " address:" + address;
      String result= appController.submitForm(name,age,address);
      Assert.assertEquals(expectedResult,result);
    }
    @Test
    public void gatversionV1Test(){
        String version="v1";
        String expectedResult="version  data is: " + version;
        String result= appController.getversion(version);
        Assert.assertEquals(expectedResult,result);
    }
    @Test
    public void gatversionV2Test(){
        String version="v2";
        String expectedResult= "new version  data is: " + version;
        String result= appController.getversion(version);
        Assert.assertEquals(expectedResult,result);
    }
    @Test
    public void submitUserdataTest(){
       UserData userData=new UserData("myname","23", "delhi",  "862435364",  "23000",  "2345678");
       UserData result= appController.submitUserdata(userData);
       Assert.assertEquals(userData.getName(),result.getName());
       Assert.assertEquals(userData.getAge(),result.getAge());
       Assert.assertEquals(userData.getAccount(),result.getAccount());
    }
    @Test
    public void addValueTest(){
        String str1="x";
        String str2="is y";
        String expectedResult="myname is name";
        //Mocking of class
        Mockito.when(mathOperation.addString(anyString(),anyString())).thenReturn(expectedResult);
        String result= appController.addValue(str1,str2);
        Assert.assertEquals(expectedResult,result);
    }
    @Test
    public void getFibbonacciTest(){
        List<Integer> expectedResult= new ArrayList<>(Arrays. asList(0,1,1,2,3,5));
        //Mocking
        Mockito.when(mathOperation.getFibbonaciNumber(anyInt())).thenReturn(expectedResult);
        List<Integer> result= appController.getFibbonacci(5);
        Assert.assertEquals(expectedResult.size(),result.size());
        for( int i=0; i<result.size();i++){
            Assert.assertEquals(expectedResult.get(i),result.get(i));
        }
    }

}
