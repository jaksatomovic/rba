package io.github.jaksatomovic.rba.web.controller;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.commons.exception.ApiException;
import io.github.jaksatomovic.rba.core.features.shared.peer.ClientPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type client controller.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@RestController
@RequestMapping ("/client")
@Api (value = "Client Service")
public class ClientController
{
    private final ClientPeer peer;

    /**
     * Instantiates a new client controller.
     *
     * @param peer the peer
     */
    public ClientController(final ClientPeer peer)
    {
        this.peer = Defense.notNull(peer, ClientPeer.class.getSimpleName());
    }

    @ApiOperation (value = "Searches for the client based on the provided identification number.")
    @PostMapping
    public @ResponseBody
    CreateClientResponse search(
        @RequestBody CreateClientRequest request
    )
        throws ApiException
    {
        return peer.create(request);
    }

    @ApiOperation (value = "Searches for the client based on the provided identification number.")
    @GetMapping
    public @ResponseBody
    SearchClientResponse search(
        @RequestParam (value = "oib") Long identificationNumber
    )
        throws ApiException
    {
        SearchClientRequest request = new SearchClientRequest();

        request.setIdentificationNumber(identificationNumber);

        return peer.search(request);
    }

    @ApiOperation (value = "Deletes the searched client by the identification number.")
    @DeleteMapping
    public @ResponseBody
    DeleteClientResponse delete(
        @RequestParam (value = "oib") Long identificationNumber
    )
        throws ApiException
    {
        final DeleteClientRequest request = new DeleteClientRequest();
        request.setIdentificationNumber(identificationNumber);

        return peer.delete(request);
    }
}
