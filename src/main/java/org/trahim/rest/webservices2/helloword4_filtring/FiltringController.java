package org.trahim.rest.webservices2.helloword4_filtring;

import ch.qos.logback.classic.db.names.SimpleDBNameResolver;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FiltringController {
    //статическая фильтрация
    @GetMapping("/filtering")
    public SameBean retrievingSameBean() {
        return new SameBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-bean")
    public List<SameBean> retrievingList() {
        return Arrays.asList(new SameBean("value1", "value2", "value3"), new SameBean("value11", "value22", "value33"));
    }


    //динамическая фильтрация
    @GetMapping("/filtering-d")
    public MappingJacksonValue retrievingSameBeanDyn() {
        SameBeanDynamic beanDynamic = new SameBeanDynamic("value1", "value2", "value3");

        //какие поля отпраялять в респонс
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");

        //нозвание фильтра, которое прописываеться в бине
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SameSimpleFilter", filter);

        MappingJacksonValue jacksonValue = new MappingJacksonValue(beanDynamic);
        jacksonValue.setFilters(filterProvider);

        return jacksonValue;
    }

    @GetMapping("filtering-d-list")
    public MappingJacksonValue retrievingDynList() {
        List<SameBeanDynamic> list = Arrays.asList(new SameBeanDynamic("1", "2", "3"), new SameBeanDynamic("4", "5", "6"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2", "value3");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SameSimpleFilter", filter);

        MappingJacksonValue jacksonValue = new MappingJacksonValue(list);
        jacksonValue.setFilters(filterProvider);

        return jacksonValue;
    }
}
