package uk.co.a6software.spring_security_roles_exploration.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleEndpointsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void everyoneEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("everyone can see this"));
    }

    @Test
    void authenticatedEndpointWhenLoggedOutIsNotAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/only-authenticated"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "authenticated")
    void authenticatedEndpointWhenLoggedInIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/only-authenticated"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("only authenticated users can see this"));
    }

    @Test
    @WithMockUser(username = "some user without ROLE_AUTHORISED")
    void authorisedAccessEndpointWithWrongRoleIsNotAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/only-authorised"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "someone", roles = "AUTHORISED")
    void authorisedAccessEndpointWithRightRoleIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/only-authorised"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("only users with an allowed role can see this"));
    }

    @Test
    void adminEndpointWhenLoggedOutIsNotAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "someone", roles = "AUTHORISED")
    void adminEndpointWhenLoggedInWithWrongRoleIsNotAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "fake admin", roles = "ADMIN")
    void adminEndpointWhenLoggedInWithRoleAdminIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("admin only"));
    }
}