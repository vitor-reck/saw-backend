package br.vitorreck.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class Category {

  @Id
  @Field(targetType = FieldType.STRING)
  private String id;

  private String name;

  @Field(name = "created_at", targetType = FieldType.TIMESTAMP)
  @CreatedDate
  private Instant createdAt;

  @Field(name = "updated_at", targetType = FieldType.TIMESTAMP)
  @LastModifiedDate
  private Instant updatedAt;
}
