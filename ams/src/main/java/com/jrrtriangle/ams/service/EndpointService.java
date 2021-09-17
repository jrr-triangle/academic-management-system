package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.entity.Endpoint;
import com.jrrtriangle.ams.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndpointService {

    final EndpointRepository endpointRepository;

    final AuthorityService authorityService;

    public EndpointService(EndpointRepository endpointRepository, AuthorityService authorityService) {
        this.endpointRepository = endpointRepository;
        this.authorityService = authorityService;
    }

    public Endpoint addUpdateEndpoint(Endpoint endpoint) {

        Endpoint endpoint1 = endpointRepository.save(endpoint);
        authorityService.getAuthorityRefresh(endpoint1);
        return endpoint1;
    }
}
