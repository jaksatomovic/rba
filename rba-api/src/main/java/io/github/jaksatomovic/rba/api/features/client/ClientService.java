package io.github.jaksatomovic.rba.api.features.client;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.commons.api.Api;
import io.github.jaksatomovic.rba.commons.exception.ApiException;

public interface ClientService
    extends Api
{
    CreateClientResponse create(CreateClientRequest request)
        throws ApiException;

    SearchClientResponse search(SearchClientRequest request)
        throws ApiException;

    DeleteClientResponse delete(DeleteClientRequest request)
        throws ApiException;
}
