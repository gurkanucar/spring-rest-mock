package com.gucardev.springrestmock.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.MediaType;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MockData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String path;

  @CreationTimestamp private LocalDate created;

  @Enumerated(EnumType.STRING)
  private ResponseType responseType = ResponseType.SUCCESS;

  @Enumerated(EnumType.STRING)
  private ContentType contentType = ContentType.REST;

  @Enumerated(EnumType.STRING)
  private HttpMethod httpMethod;

  private Integer successStatus = 200;
  private Integer failureStatus = 400;

  @Column(length = 50000)
  private String successResponse;

  @Column(length = 50000)
  private String failureResponse;

  @Transient private String chosenResponse;

  @Transient private Integer chosenStatus;

  @Transient private MediaType mediaType = MediaType.APPLICATION_JSON;
}
