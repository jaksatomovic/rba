package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Search client request validator.
 */
@Service
public class SearchClientRequestValidator
    implements RequestValidator<SearchClientRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    /**
     * Instantiates a new Search client request validator.
     *
     * @param baseRequestValidator the base request validator
     */
    public SearchClientRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final SearchClientRequest request)
    {
        baseRequestValidator.validate(request);
        Defense.notNull(request.getIdentificationNumber(), "identification number");
        logger.debug("[VALIDATION] - Search Client Request - [OK]");
    }
}