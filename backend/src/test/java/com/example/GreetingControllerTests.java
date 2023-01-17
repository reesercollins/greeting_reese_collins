package com.example;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/greeting")).andExpect(status().isCreated())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void shouldReturnGreeting() throws Exception {
        this.mockMvc.perform(post("/greeting?name=Bobby")).andExpect(status().isCreated())
                .andExpect(content().string(containsString("Hello, Bobby")));
    }

    @Test
    public void shouldReturnGreetingHistory() throws Exception {
        this.mockMvc.perform(post("/greeting?name=Test1")).andExpect(status().isCreated());
        this.mockMvc.perform(post("/greeting?name=Test2")).andExpect(status().isCreated());
        this.mockMvc.perform(get("/greeting")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Test1")))
                .andExpect(content().string(containsString("Hello, Test2")));
    }

    @Test
    public void shouldEditGreeting() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/greeting?name=Editable")).andExpect(status().isCreated())
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        this.mockMvc.perform(put("/greeting?id={id}", id).contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\": \"Hello, Edited\"}")).andExpect(status().isOk());
        this.mockMvc.perform(get("/greeting")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Edited")))
                .andExpect(content().string(not(containsString("Hello, Editable"))));
    }

    @Test
    public void shouldBeAuditable() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/greeting?name=Auditable")).andExpect(status().isCreated())
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        // Audit should contain the greeting object in full
        this.mockMvc.perform(get("/greeting/history?id={id}", id)).andExpect(status().isOk())
                .andExpect(content().string(containsString(result.getResponse().getContentAsString())));
    }

}
