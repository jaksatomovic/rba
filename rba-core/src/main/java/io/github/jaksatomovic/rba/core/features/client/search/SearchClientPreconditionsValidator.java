package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.validator.context.PreconditionsValidator;
import org.springframework.stereotype.Service;

@Service
public class SearchClientPreconditionsValidator
    implements PreconditionsValidator<SearchClientContext>
{
    @Override
    public void validate(final SearchClientContext context)
    {
        Defense.notNull(context.getExistingClient().get(), "client");
        Defense.notNull(context.getExistingClient().get().getId(), "client id");
        Defense.notNull(context.getExistingClient().get().getFirstName(), "fist name");
        Defense.notNull(context.getExistingClient().get().getLastName(), "last name");
        Defense.notNull(context.getExistingClient().get().getIdentificationNumber(), "identification number");
        Defense.notNull(context.getExistingClient().get().getStatus(), "status");
        // TODO
        //        throw new AppException(ApplicationResponseCode.ENTITY_NOT_FOUND);
    }
}

