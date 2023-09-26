package com.gucardev.springrestmock.util;

import java.net.InetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnvUtil {

  private final Environment environment;

  private String port;
  private String hostname;

  @Value("${mock.api.handler.endpoint}")
  private String handlerEndpoint;

  public String getPort() {
    if (port == null) port = environment.getProperty("local.server.port");
    return port;
  }

  public Integer getPortAsInt() {
    return Integer.valueOf(getPort());
  }

  public String getHostname() throws Exception {
    if (hostname == null) hostname = InetAddress.getLocalHost().getHostAddress();
    return hostname;
  }

  public String getServerUrlPrefix() throws Exception {
    return "http://" + getHostname() + ":" + getPort() + handlerEndpoint;
  }
}
