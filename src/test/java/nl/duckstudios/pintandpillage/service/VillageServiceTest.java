package nl.duckstudios.pintandpillage.service;

import nl.duckstudios.pintandpillage.dao.VillageDataMapper;
import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.User;
import nl.duckstudios.pintandpillage.Exceptions.SettleConditionsNotMetException;
import nl.duckstudios.pintandpillage.helper.ResourceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VillageServiceTest {

    @Mock
    private VillageDataMapper villageDataMapper;

    @Mock
    private ResourceManager resourceManager;

    @Mock
    private WorldService worldService;

    @Mock
    private DistanceService distanceService;

    private VillageService villageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        villageService = new VillageService(villageDataMapper, resourceManager, worldService, distanceService);
    }

    @Test
    void checkIsValidCreatingSpot_ShouldThrowException_WhenNoJarlAvailable() {
        Village village = new Village();
        village.setUser(new User());
        Coord newPosition = new Coord(1, 1);
        
        assertThrows(SettleConditionsNotMetException.class, () -> {
            villageService.checkIsValidCreatingSpot(village, newPosition);
        });
    }

    @Test
    void getListOfVillagesFromUser_ShouldReturnVillages_WhenUserHasVillages() {
        long userId = 1L;
        List<Village> expectedVillages = new ArrayList<>();
        expectedVillages.add(new Village());
        when(villageDataMapper.getVillages(userId)).thenReturn(expectedVillages);
        
        List<Village> result = villageService.getListOfVillagesFromUser(userId);
        
        assertNotNull(result);
        assertEquals(expectedVillages.size(), result.size());
        verify(villageDataMapper).getVillages(userId);
    }
} 