package com.flipkart.collectionapplication.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@JsonDeserialize(using=LocalDateTimeDeserializer.class)
public class Auditable {

  private static final DateTimeZone timeZone = DateTimeZone.forID("Asia/Kolkata");

  @Temporal(TemporalType.TIMESTAMP)
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime updatedAt;

  @PrePersist
  public void onCreate() {
    createdAt = LocalDateTime.now(timeZone);;
    onUpdate();
  }

  @PreUpdate
  public void onUpdate() {
    updatedAt = LocalDateTime.now(timeZone);
  }
}

