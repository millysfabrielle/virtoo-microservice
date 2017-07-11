package br.edu.unievangelica.core.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponse {

    protected Object content;

    protected Map<JsonResponseType, List<String>> messages = new HashMap<>();



    public void setMessages(Map<JsonResponseType, List<String>> messages) {
        this.messages = messages;
    }


}
