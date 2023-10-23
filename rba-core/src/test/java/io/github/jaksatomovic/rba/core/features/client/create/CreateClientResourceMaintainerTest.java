package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.core.features.shared.AbstractTest;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import io.github.jaksatomovic.rba.core.persistence.store.FileStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        CreateClientResourceMaintainer.class,
    }
)
class CreateClientResourceMaintainerTest
    extends AbstractTest
{

    @Autowired
    private CreateClientResourceMaintainer maintainer;

    @MockBean
    private ClientStore clientStore;

    @MockBean
    private FileStore fileStore;

    @Test
    void resolve()
    {
        Assertions.assertDoesNotThrow(() -> maintainer.resolve(getCreateClientContext(prepareCreateClientRequest())));
    }
}