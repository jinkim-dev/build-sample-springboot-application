package com.jindev.pipeline.build;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jindev.pipeline.api.build.Build;
import com.jindev.pipeline.api.build.BuildController;
import com.jindev.pipeline.api.build.BuildService;
import com.offbytwo.jenkins.model.JobWithDetails;

import static org.mockito.Mockito.eq;

@RunWith(SpringRunner.class)
@WebMvcTest(BuildController.class)
public class BuildControllerTest {

  @Autowired private MockMvc mockMvc;
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
    Mockito.when(buildService.get(1)).thenReturn(Optional.ofNullable(build));
    Mockito.when(buildService.getJob(build.getAppName())).thenReturn(details);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/builds/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void testSave() throws Exception {
    Build saveBuild =
        Build.builder()
            .id(1L)
            .appName("jpetstore")
            .buildTool("maven")
            .gitAddress("https://github.com/mybatis/jpetstore-6.git")
            .build();
    Mockito.when(buildService.save(eq(build))).thenReturn(saveBuild);
    saveBuild = buildService.save(build);
    Assert.assertNotNull(saveBuild);
    Assert.assertSame(saveBuild.getId(), 1L);
    Assert.assertEquals(saveBuild.getAppName(), "jpetstore");
    Assert.assertEquals(saveBuild.getBuildTool(), "maven");
    Assert.assertEquals(saveBuild.getGitAddress(), "https://github.com/mybatis/jpetstore-6.git");
  }
}
