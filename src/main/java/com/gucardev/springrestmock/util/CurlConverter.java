package com.gucardev.springrestmock.util;

import com.gucardev.springrestmock.model.MockData;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurlConverter {

  private final EnvUtil envUtil;

  public String convertToCurl(MockData mockData) throws Exception {
    Objects.requireNonNull(mockData);
    return "curl -X "
            + mockData.getHttpMethod()
            + " \""
            + envUtil.getServerUrlPrefix()
            + mockData.getPath()
            + "\"";
  }

}
