package com.jindev.pipeline;

import com.jindev.pipeline.build.BuildController;
import com.jindev.pipeline.build.BuildService;
import com.jindev.pipeline.build.Build;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(BuildController.class)
public class BuildControllerTest {

    @MockBean
    private BuildService buildService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void buildsShouldReturnMessageFromService() throws Exception {
        Build build = Build.builder().appName("jpetstore")
                .buildTool("maven")
                .gitAddress("https://github.com/mybatis/jpetstore-6.git")
                .build();
        List<Build> builds = Arrays.asList(build);
        Mockito.when(buildService.getList()).thenReturn(builds);
        mockMvc.perform(MockMvcRequestBuilders.get("/jindev/builds"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
