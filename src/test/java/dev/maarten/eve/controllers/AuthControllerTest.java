package dev.maarten.eve.controllers;

import dev.maarten.eve.models.contract.LoginLinkContract;
import dev.maarten.eve.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc controller;

    @Mock
    private AuthService authService;

    @Test
    void GetLoginLink() throws Exception {
        //Arrange variables
        LoginLinkContract loginLinkContract = LoginLinkContract.builder()
                .url("test")
                .build();
        //Arrange Mocks
        when(authService.getLoginLink()).thenReturn(loginLinkContract);
        //Act
        controller.perform(get("/login/link"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.url").value("test"));

        //Assert
    }

}
