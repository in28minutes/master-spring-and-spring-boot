package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplNewMockitoTest {

    @Mock
    private DataService dataService;

    @InjectMocks
    private SomeBusinessImpl business;

    // thenAnswer() feature
    @Test
    void findTheGreatest_dynamicAnswer() {
        var counter = new AtomicInteger();
        when(dataService.retrieveAllData())
                .thenAnswer(_ -> {
                    if(counter.getAndIncrement() == 0)
                        return new int[] {10, 20};
                    return new int[] {100, 200};
                });

        assertEquals(20, business.findTheGreatestFromAllData());
        assertEquals(200, business.findTheGreatestFromAllData());
    }

    // doAnswer feature
    @Test
    void storeTheGreatest_doAnswer() {
        when(dataService.retrieveAllData()).thenReturn(new int[] {15, 40, 25});
        doAnswer(invocation -> {
            Integer value = invocation.getArgument(0);
            assertEquals(40, value);
            return null;
        }).when(dataService).storeGreatest(anyInt());

        business.storeTheGreatestFromAllData();
        verify(dataService).storeGreatest(40);
    }

    // thenThrow() → methods with return values
    @Test
    void findTheGreatestFromAllData_whenDataServiceFails() {

        when(dataService.retrieveAllData())
                .thenThrow(new RuntimeException("Database unavailable"));

        assertThrows(RuntimeException.class,
                () -> business.findTheGreatestFromAllData());
    }

    // doThrow() → void methods
    @Test
    void storeTheGreatestFromAllData_whenStoreFails() {

        when(dataService.retrieveAllData())
                .thenReturn(new int[] {10, 20, 30});

        doThrow(new RuntimeException("Unable to store"))
                .when(dataService)
                .storeGreatest(anyInt());

        assertThrows(RuntimeException.class,
                () -> business.storeTheGreatestFromAllData());
    }

    // Verifying Order with <code>InOrder</code>
    @Test
    void verifyMethodOrder() {

        when(dataService.retrieveAllData())
                .thenReturn(new int[] {10, 20, 30});

        business.storeTheGreatestFromAllData();

        InOrder inOrder = inOrder(dataService);

        inOrder.verify(dataService).retrieveAllData();
        inOrder.verify(dataService).storeGreatest(30);
    }

    // verifyNoInteractions()
    @Test
    void onlyFindMethodShouldNotStoreAnything() {

        when(dataService.retrieveAllData())
                .thenReturn(new int[] {10, 20, 30});

        business.findTheGreatestFromAllData();

        verify(dataService).retrieveAllData();
        verify(dataService, never()).storeGreatest(anyInt());
    }
}
