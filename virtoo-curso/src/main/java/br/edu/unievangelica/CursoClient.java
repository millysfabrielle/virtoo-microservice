package br.edu.unievangelica;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient("resource")
public interface CursoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<Message> getItem();

}

