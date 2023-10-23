package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.commons.exception.ApiException;
import io.github.jaksatomovic.rba.commons.utility.ApplicationResponseCode;
import io.github.jaksatomovic.rba.core.exception.AppException;
import io.github.jaksatomovic.rba.core.features.shared.validator.context.PreconditionsValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateClientPreconditionsValidator
    implements PreconditionsValidator<CreateClientContext>
{
    @Override
    public void validate(final CreateClientContext context)
    {
        if (context.getExistingClient().isPresent())
        {
            throw new AppException(ApplicationResponseCode.ENTITY_ALREADY_EXISTS);
        }
    }
}

