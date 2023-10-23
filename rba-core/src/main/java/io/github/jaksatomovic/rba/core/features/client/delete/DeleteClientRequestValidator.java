package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Delete client request validator.
 */
@Service
public class DeleteClientRequestValidator
    implements RequestValidator<DeleteClientRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    /**
     * Instantiates a new delete client request validator.
     *
     * @param baseRequestValidator the base request validator
     */
    public DeleteClientRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final DeleteClientRequest request)
    {
        baseRequestValidator.validate(request);
        Defense.notNull(request.getIdentificationNumber(), "identification number");
        logger.debug("[VALIDATION] - Delete Client Request - [OK]");
    }
}