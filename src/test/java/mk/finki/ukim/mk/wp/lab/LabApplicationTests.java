package mk.finki.ukim.mk.wp.lab;

import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.model.Manufacturer;
import mk.finki.ukim.mk.wp.lab.service.BalloonService;
import mk.finki.ukim.mk.wp.lab.service.LoginService;
import mk.finki.ukim.mk.wp.lab.service.ManufacturerService;
import mk.finki.ukim.mk.wp.lab.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;

    @Autowired
    LoginService loginService;

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    OrderService orderService;

    private static Balloon b1;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData() {
        if(!dataInitialized){
            m1 = manufacturerService.save("m1", "m1", "m1").get();
            Manufacturer m2 = manufacturerService.save("m2", "m2", "m2").get();

            b1 = balloonService.saveBalloon("b1", "b1", m1.getId()).get();
            balloonService.saveBalloon("b2", "b2", m2.getId());

            dataInitialized = true;
        }
    }


    @Test
    void contextLoads() {
    }


    @Test
    public void testGetBalloon() throws Exception{
        MockHttpServletRequestBuilder balloonReq = MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(balloonReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.view().name("listBalloons"));
    }
}
