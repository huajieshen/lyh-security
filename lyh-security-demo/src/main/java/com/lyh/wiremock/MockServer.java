package com.lyh.wiremock;


//import com.github.tomakehurst.wiremock.client.WireMock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

//import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;


/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/28 0:50
 */
public class MockServer {

  public static void main(String[] args) throws IOException {
//    WireMock.configureFor(9000);
    WireMock.configureFor(8062);

    removeAllMappings();

    mock("/order/1", "01");
    mock("/order/2", "02");

  }

  private static void mock(String url, String fileName) throws IOException {
    ClassPathResource fileSource = new ClassPathResource("/mock/response/"+fileName + ".txt");
    String content = FileUtils.readFileToString(fileSource.getFile(), "utf-8");
    WireMock.stubFor(get(WireMock.urlPathEqualTo(url))
            .willReturn(aResponse().withBody(content)
                    .withStatus(200)
            ));
  }
}
