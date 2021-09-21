package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.entity.Endpoint;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.repository.EndpointRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("authservice")
public class AuthorityService {


    final EndpointRepository endpointRepository;

    public AuthorityService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }


//    @PostConstruct
//    public void insertIn() {
//        Endpoint endpoint = new Endpoint();
//        Role role = new Role();
//        role.setRoleId(3L);
//        endpoint.setEndpointname("testing2");
//        Set<Role> g = new HashSet<>();
//        g.add(role);
//        endpoint.setRoles(g);
//        endpointRepository.save(endpoint);
//    }

    @Cacheable(value = "getAuthorityCache", key = "#key")
    public Set<String> getAuthority(String key) {
        Optional<Endpoint> endpoint = endpointRepository.findByEndpointname(key);
        System.out.println("getAuthorityCache entered");
        Set<String> auth = new HashSet<>();
        if(endpoint.isPresent()) {
            Set<Role> allrole = endpoint.get().getRoles();
            for(Role role: allrole) {
                auth.add(role.getRolename());
            }
        }
        return auth;
    }

    @CacheEvict(value = "getAuthorityCache", key = "#endpoint.endpointname")
    public void getAuthorityRefresh(Endpoint endpoint) {

        System.out.println("getAuthorityCachePut entered");

    }

}
