package org.trahim.rest.webservices2.helloword_versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    //через разные имена
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bpb Charli");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bpb", "Charli"));
    }

    //через параметры
    @GetMapping(value = "person/param", params = "version1")
    public PersonV1 param1() {
        return new PersonV1("Bpb Charli");
    }

    @GetMapping(value = "person/param", params = "version2")
    public PersonV2 param2() {
        return new PersonV2(new Name("Bpb", "Charli"));
    }

    //чрез headers
    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 headers1() {
        return new PersonV1("Bpb Charli");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 headers2() {
        return new PersonV2(new Name("Bpb", "Charli"));
    }

    //через producers
    @GetMapping(value = "person/producer", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producer1() {
        return new PersonV1("Bpb Charli");
    }

    @GetMapping(value = "person/producer", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producer2() {
        return new PersonV2(new Name("Bpb", "Charli"));
    }

}
