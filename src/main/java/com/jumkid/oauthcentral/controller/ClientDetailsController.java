package com.jumkid.oauthcentral.controller;

import com.jumkid.oauthcentral.controller.dto.ClientDetails;
import com.jumkid.oauthcentral.controller.response.CommonResponse;
import com.jumkid.oauthcentral.service.ClientDetailsService;
import com.jumkid.oauthcentral.utils.ClientDetailsField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/client-details")
public class ClientDetailsController {

    private final ClientDetailsService clientDetailsService;

    @Autowired
    public ClientDetailsController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping(value = "{clientDetailsId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDetails get(@PathVariable Integer clientDetailsId) {
        return clientDetailsService.getClientDetails(clientDetailsId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientDetails add(@NotNull @Valid @RequestBody ClientDetails clientDetails){
        return clientDetailsService.saveClientDetails(clientDetails);
    }

    @GetMapping(path = "/field/{fieldName}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getSingleFieldOfAll(@PathVariable String fieldName) {
        return clientDetailsService.getSingleFieldOfAll(ClientDetailsField.of(fieldName));
    }

    @PutMapping(value = "/{clientDetailsId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDetails update(@PathVariable Integer clientDetailsId,
                                @NotNull @Valid @RequestBody ClientDetails clientDetails) {
        return clientDetailsService.updateClientDetails(clientDetailsId, clientDetails);
    }

    @DeleteMapping(value = "{clientDetailsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse delete(@PathVariable Integer clientDetailsId) {
        clientDetailsService.deleteClientDetails(clientDetailsId);
        return new CommonResponse("client details is deleted");
    }

}
