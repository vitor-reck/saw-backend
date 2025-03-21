package br.vitorreck.app.domain.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

  @Id
  @Field(targetType = FieldType.STRING)
  private String id;

  private String name;
  private String description;
  private BigDecimal price;

  @Indexed(unique = true)
  @Field(targetType = FieldType.DECIMAL128)
  @DocumentReference(lazy = true)
  private Category category;

  @Field(targetType = FieldType.INT32)
  private Integer stock;

  @Field(name = "created_at", targetType = FieldType.TIMESTAMP)
  @CreatedDate
  private Instant createdAt;

  @Field(name = "updated_at", targetType = FieldType.TIMESTAMP)
  @LastModifiedDate
  private Instant updatedAt;
}
