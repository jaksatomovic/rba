package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

/**
 * The type Delete client response generator.
 */
@Service
public class DeleteClientResponseGenerator
    implements ResponseGenerator<DeleteClientResponse, DeleteClientContext>
{
    @Override
    public DeleteClientResponse generateResponse(final DeleteClientContext context)
    {
        return new DeleteClientResponse(context.getOriginalRequest(), ResponseCode.OK);
    }
}
