package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type create client request validator.
 */
@Service
public class CreateClientRequestValidator
    implements RequestValidator<CreateClientRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    /**
     * Instantiates a new Create client request validator.
     *
     * @param baseRequestValidator the base request validator
     */
    public CreateClientRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final CreateClientRequest request)
    {
        baseRequestValidator.validate(request);
        Defense.notNull(request.getFirstName(), "first name");
        Defense.notNull(request.getLastName(), "last name");
        Defense.notNull(request.getIdentificationNumber(), "identification number");
        logger.debug("[VALIDATION] - Create Client Request - [OK]");
    }
}