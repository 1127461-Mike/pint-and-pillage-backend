package nl.duckstudios.pintandpillage.service;

import nl.duckstudios.pintandpillage.dao.VillageDataMapper;
import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.VillageUnit;
import nl.duckstudios.pintandpillage.entity.production.Jarl;
import nl.duckstudios.pintandpillage.helper.ResourceManager;
import nl.duckstudios.pintandpillage.model.UnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VillageServiceRefactoredTest {

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
        villageService = new VillageService(villageDataMapper, resourceManager, worldService, distanceService);
    }

    @Test
    void hasJarl_ShouldReturnTrue_WhenVillageHasJarl() {
        Village village = new Village();
        village.addUnit(new Jarl(), 1);
        
        boolean result = villageService.hasJarl(village);
        
        assertTrue(result);
    }

    @Test
    void hasJarl_ShouldReturnFalse_WhenVillageHasNoJarl() {
        Village village = new Village();
        village.addUnit(new Jarl(), 0);
        
        boolean result = villageService.hasJarl(village);
        
        assertFalse(result);
    }

    @Test
    void calculateDistance_ShouldReturnCorrectDistance() {
        Coord pos1 = new Coord(0, 0);
        Coord pos2 = new Coord(3, 4);
        when(distanceService.calculateDistance(pos1, pos2)).thenReturn(5);
        
        int result = villageService.calculateDistance(pos1, pos2);
        
        assertEquals(5, result);
        verify(distanceService).calculateDistance(pos1, pos2);
    }

    @Test
    void isValidCreateDistance_ShouldReturnTrue_WhenWithinMaxDistance() {
        Village village = new Village();
        village.setPositionX(0);
        village.setPositionY(0);
        Coord newPosition = new Coord(3, 4);
        when(distanceService.calculateDistance(any(Coord.class), eq(newPosition))).thenReturn(5);
        
        boolean result = villageService.isValidCreateDistance(village, newPosition);
        
        assertTrue(result);
    }

    @Test
    void isValidCreateDistance_ShouldReturnFalse_WhenBeyondMaxDistance() {
        Village village = new Village();
        village.setPositionX(0);
        village.setPositionY(0);
        Coord newPosition = new Coord(10, 10);
        when(distanceService.calculateDistance(any(Coord.class), eq(newPosition))).thenReturn(10);
        
        boolean result = villageService.isValidCreateDistance(village, newPosition);
        
        assertFalse(result);
    }

    @Test
    void isValidCreateDistance_ShouldReturnFalse_WhenPositionIsNull() {
        Village village = new Village();
        
        boolean result = villageService.isValidCreateDistance(village, null);

        assertFalse(result);
    }

    @Test
    void isValidPosition_ShouldReturnTrue_WhenPositionIsValid() {
        Coord position = new Coord(5, 5);
        int maxX = 10;
        int maxY = 10;
        
        boolean result = villageService.isValidPosition(position, maxX, maxY);
        
        assertTrue(result);
    }

    @Test
    void isValidPosition_ShouldReturnFalse_WhenPositionIsNull() {
        int maxX = 10;
        int maxY = 10;
        
        boolean result = villageService.isValidPosition(null, maxX, maxY);

        assertFalse(result);
    }

    @Test
    void isValidPosition_ShouldReturnFalse_WhenPositionIsOutOfBounds() {
        Coord position = new Coord(15, 5);
        int maxX = 10;
        int maxY = 10;
        
        boolean result = villageService.isValidPosition(position, maxX, maxY);
        
        assertFalse(result);
    }
} 