package io.github.jaksatomovic.rba.core.features.shared.peer;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientOperation;
import io.github.jaksatomovic.rba.core.features.client.delete.DeleteClientOperation;
import io.github.jaksatomovic.rba.core.features.client.search.SearchClientOperation;
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
        ClientPeer.class,
    }
)
class ClientPeerTest
{
    @Autowired
    private ClientPeer peer;

    @MockBean
    private SearchClientOperation searchStatisticsOperation;
    @MockBean
    private DeleteClientOperation deleteStatisticsOperation;
    @MockBean
    private CreateClientOperation createClientOperation;

    @Test
    void search()
    {
        Assertions.assertDoesNotThrow(() -> peer.search(new SearchClientRequest()));
    }

    @Test
    void delete()
    {
        Assertions.assertDoesNotThrow(() -> peer.delete(new DeleteClientRequest()));
    }

    @Test
    void create()
    {
        Assertions.assertDoesNotThrow(() -> peer.create(new CreateClientRequest()));
    }
}