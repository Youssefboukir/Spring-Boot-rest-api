package com.contact;

import com.contact.controller.ContactController;
import com.contact.dto.ContactDto;
import com.contact.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Test
    void testGetContacts() throws Exception {
        List<ContactDto> contacts=Arrays.asList(
                new ContactDto("1","youssef","youssefboukir@gmail.com","+21256876545"),
                new ContactDto("2","test","test@gmail.com","+21256876545")
        );
        // Mock the ContactService to return a list of two books
        Mockito.when(contactService.getAllContact()).thenReturn(contacts);

        // Perform an HTTP GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/contact/list"))
                // Assert that the response contains the two contacts
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idContact").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("youssef"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("youssefboukir@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value("+21256876545"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].idContact").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("test@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phone").value("+21256876545"));
    }

    @Test
    void testGetContactById() throws Exception {
        // Create a new ContactDto object
        ContactDto contactDto = new ContactDto("1","youssef","youssefboukir@gmail.com","+21256876545");
        Mockito.when(contactService.getContactById("1")).thenReturn(contactDto);
        // Perform an HTTP GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/contact/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("idContact").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("youssef"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("youssefboukir@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("phone").value("+21256876545"));

    }
    @Test
    void testAddContact() throws Exception {
        // Create a new ContactDto object
        ContactDto contactDto = new ContactDto("1","youssef","youssefboukir@gmail.com","+21256876545");
        // Mock the contactService's saveContact() method to return the ContactDto object
        Mockito.when(contactService.saveContact(contactDto)).thenReturn(contactDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/contact/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(contactDto)))
                // Ensure that the response body contains the expected value
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idContact").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("youssef"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("youssefboukir@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+21256876545"));
    }

    @Test
    void testUpdateContact() throws Exception {
        ContactDto contactDto = new ContactDto("1","youssef","youssefboukir@gmail.com","+21256876545");
        // Mock the contactService's updateContact() method to return the ContactDto object
        Mockito.when(contactService.updateContact(contactDto,"1")).thenReturn(contactDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/contact/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(contactDto)))
                // Ensure that the response body contains the expected value
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idContact").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("youssef"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("youssefboukir@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+21256876545"));
    }

    @Test
    void testDeleteContact() throws Exception {
        // Create a new ContactDto object
        ContactDto contactDto = new ContactDto("1","youssef","youssefboukir@gmail.com","+21256876545");
        // Mock the contactService's deleteContact() method to return the ContactDto object
        Mockito.when(contactService.deleteContact("1")).thenReturn(contactDto);
        mockMvc.perform(MockMvcRequestBuilders.delete("/contact/delete/1"))
                // Ensure that the response body contains the expected value
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idContact").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("youssef"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("youssefboukir@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+21256876545"));
    }
}


