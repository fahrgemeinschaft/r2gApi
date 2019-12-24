package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.Thing;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ContactPointDto extends Thing {

    String email;
    String faxnumber;
    String telephone;

}