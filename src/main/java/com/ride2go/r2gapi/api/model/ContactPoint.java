package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class ContactPoint extends Thing {

    String email;
    String faxnumber;
    String telephone;

}