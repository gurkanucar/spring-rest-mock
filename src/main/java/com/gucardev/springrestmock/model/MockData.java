package com.gucardev.springrestmock.model;

import java.time.LocalDate;
import javax.persistence.*;

import com.gucardev.springrestmock.HttpMethod;
import com.gucardev.springrestmock.ResponseType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
  private ResponseType responseType;

  @Enumerated(EnumType.STRING)
  private HttpMethod httpMethod;

  public Integer successStatus;
  public Integer failureStatus;

  @Column(length = 2000)
  public String successResponse;

  @Column(length = 2000)
  public String failureResponse;
}