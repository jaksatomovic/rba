package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.validator.context.PreconditionsValidator;
import org.springframework.stereotype.Service;

@Service
public class DeleteClientPreconditionsValidator
    implements PreconditionsValidator<DeleteClientContext>
{
    @Override
    public void validate(final DeleteClientContext context)
    {
        Defense.notNull(context.getExistingClient().get(), "existing client");
        Defense.notNull(context.getExistingClient().get().getId(), "id");
        Defense.notNull(context.getExistingClient().get().getFirstName(), "fist name");
        Defense.notNull(context.getExistingClient().get().getLastName(), "last name");
        Defense.notNull(context.getExistingClient().get().getIdentificationNumber(), "identification number");
        Defense.notNull(context.getExistingClient().get().getStatus(), "status");
    }
}

