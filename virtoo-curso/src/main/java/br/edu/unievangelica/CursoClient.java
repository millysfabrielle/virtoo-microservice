package br.edu.unievangelica;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient(name="resource", url = "http://localhost:9000")
public interface CursoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/resource")
    List<Message> getItem();

}

