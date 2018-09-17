package org.trahim.rest.webservices2.helloword4_filtring;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Это пример ствтической фильтрации
@JsonIgnoreProperties(value = {"value2", "value3"})// аналогично, если писать над каждым полем @JsonIgnoreD
public class SameBean {

    private String value1;
//    @JsonIgnore
    private String value2;
//    @JsonIgnore
    private String value3;


//    @Transient - field is NOT persisted and not serialised and not deserialized
//
//    @JsonIgnore - field is persisted but not serialised and not deserialized
//пример статической фильтрации
}
