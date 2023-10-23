package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

/**
 * The type create client response generator.
 */
@Service
public class CreateClientResponseGenerator
    implements ResponseGenerator<CreateClientResponse, CreateClientContext>
{
    @Override
    public CreateClientResponse generateResponse(final CreateClientContext context)
    {
        return new CreateClientResponse(context.getOriginalRequest(), ResponseCode.OK);
    }
}
