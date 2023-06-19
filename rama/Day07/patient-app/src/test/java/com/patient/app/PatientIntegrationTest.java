package com.patient.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.app.controller.PatientController;
import com.patient.app.exception.PatientIdNotFoundException;
import com.patient.app.exception.PatientNameNotFoundException;
import com.patient.app.model.Patient;
import com.patient.app.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({PatientController.class})
@ActiveProfiles(value = "test")
public class PatientIntegrationTest {
    @InjectMocks
    PatientController patientController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PatientService patientService;
    @Value("${patient.save.url}")
    String saveurl;

    @Value("${patient.update.url}")
    String updateUrl;

    @Value("${patient.detail.url}")
    String detailurl;

    @Test
    public void testSavePatientPost() throws Exception {
        Patient patient=new Patient("pat01","pat_test","30","Male","fever");
        ResultActions responseEntity  = mockMvc.perform(post(saveurl).contentType(MediaType.APPLICATION_JSON)
                .content(asObjectToJsonString(patient)).accept(MediaType.APPLICATION_JSON));
        responseEntity.andExpect(status().isOk());
        String result = responseEntity.andReturn().getResponse().getContentAsString();
        Patient patientResult= asJsonStringToObject(result);
        assertNotNull(patientResult);
        assertEquals(patientResult.getId(),"pat01");
    }

    @Test
    public void testSavePatientWithoutIdPost() throws Exception {
        Patient patient=new Patient(null,"pat_test","30","Male","fever");
        ResultActions responseEntity  = mockMvc.perform(post(saveurl).contentType(MediaType.APPLICATION_JSON)
                .content(asObjectToJsonString(patient)).accept(MediaType.APPLICATION_JSON));
        responseEntity.andExpect(status().isNotFound());
    }
    @Test
    public void testSavePatientWithoutNamePost() throws Exception {
        Patient patient=new Patient("pat01",null,"30","Male","fever");
        ResultActions responseEntity  = mockMvc.perform(post(saveurl).contentType(MediaType.APPLICATION_JSON)
                .content(asObjectToJsonString(patient)).accept(MediaType.APPLICATION_JSON));
        responseEntity.andExpect(status().isBadRequest());
    }

    @Test
    public void testDetailPatientPost() throws Exception {
        Patient patient=new Patient("pat01","pat_test","30","Male",null);
        ResultActions responseEntity  = mockMvc.perform(post(detailurl).header("disease","fever").contentType(MediaType.APPLICATION_JSON)
                .content(asObjectToJsonString(patient)).accept(MediaType.APPLICATION_JSON));
        responseEntity.andExpect(status().isOk());
        String result = responseEntity.andReturn().getResponse().getContentAsString();
        Patient patientResult= asJsonStringToObject(result);
        assertNotNull(patientResult);
        assertEquals(patientResult.getId(),"pat01");
        assertEquals(patientResult.getName(),"pat_test");
        assertEquals(patientResult.getDisease(),"fever");
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

    private Patient asJsonStringToObject( String result) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            Patient patient= mapper.readValue(result, Patient.class);
            return patient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
