package com.ride2go.r2gapi.mapper;


import com.neovisionaries.i18n.CountryCode;
import com.ride2go.core.location.PostalAddress;
import com.ride2go.r2gapi.api.dto.PostalAddressDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class PostalAddressMapper {

    @Mapping(target = "countryCode",ignore = true)
    public abstract PostalAddressDto map(PostalAddress postalAddress);

    @Mapping(target = "countryCode",ignore = true)
    public abstract PostalAddress reverseMap(PostalAddressDto postalAddressDto);

    @AfterMapping
    protected void mapCountryCodeToString(PostalAddress postalAddress, @MappingTarget PostalAddressDto postalAddressDto){
        postalAddressDto.setCountryCode(postalAddress.getCountryCode().getAlpha3());
    }

    @AfterMapping
    protected void mapStringToCountryCode(PostalAddressDto postalAddressDto, @MappingTarget PostalAddress postalAddress ){
        postalAddress.setCountryCode(CountryCode.getByAlpha3Code(postalAddressDto.getCountryCode()));
    }


}
