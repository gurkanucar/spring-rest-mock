package com.gucardev.springrestmock.model;

import java.time.LocalDate;
import javax.persistence.*;
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

  private Integer successStatus;
  private Integer failureStatus;

  @Column(length = 50000)
  private String successResponse;

  @Column(length = 50000)
  private String failureResponse;

  @Transient private String chosenResponse;

  @Transient private Integer chosenStatus;
}
