package org.trahim.rest.webservices2.helloword4_filtring;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Это пример динамической фмлтрации

@JsonFilter("SameSimpleFilter")
public class SameBeanDynamic {

    private String value1;
    private String value2;
    private String value3;
}
