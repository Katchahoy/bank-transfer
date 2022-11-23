package com.bank.bank.transfer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransferResourceTest {

  @Autowired
  private MockMvc mockMvc;

  @Value("classpath:transfer/sample1.json")
  private Resource transferRequest1;

  @Value("classpath:transfer/sample2.json")
  private Resource transferRequest2;

  @Test
  void testTransfer1() throws Exception {
    String jsonBody = readResourceFile(transferRequest1);
    assertThat(jsonBody).isNotEmpty();

    mockMvc.perform(
            post("/api/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonBody)
    ).andExpect(
            status().is(201)
    );
  }

  @Test
  void testTransfer2() throws Exception {
    String jsonBody = readResourceFile(transferRequest1);
    assertThat(jsonBody).isNotEmpty();

    mockMvc.perform(
            post("/api/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonBody)
    ).andExpect(
            status().is(201)
    );
  }

  @Test
  void testDenyInvalidTransferRequest() throws Exception {

    mockMvc.perform(
            post("/api/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpect(
            status().is(422)
    );
  }

  private String readResourceFile(Resource resource) throws IOException {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
      return FileCopyUtils.copyToString(reader);
    }
  }
}
