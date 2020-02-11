package com.jindev.pipeline.build;

import com.jindev.pipeline.api.build.*;
import com.jindev.pipeline.jenkins.JenkinsAPi;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

  @MockBean private BuildService buildService;
  @MockBean private ModelMapper modelMapper;
  private Build build;
  private JobWithDetails details;

  @Before
  public void setup() {
    build =
        Build.builder()
            .appName("jpetstore")
            .buildTool("maven")
            .gitAddress("https://github.com/mybatis/jpetstore-6.git")
            .build();
    details = new JobWithDetails();
  }

  @Autowired private MockMvc mockMvc;

  @Test
  public void buildsShouldReturnMessageFromService() throws Exception {
    List<Build> builds = Arrays.asList(build);
    Mockito.when(buildService.getList()).thenReturn(builds);
    Mockito.when(buildService.getJob(build.getAppName())).thenReturn(details);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/builds"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void buildShouldReturnMessageFromService() throws Exception {
    Mockito.when(buildService.get(1)).thenReturn(build);
    Mockito.when(buildService.getJob(build.getAppName())).thenReturn(details);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/builds/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}
