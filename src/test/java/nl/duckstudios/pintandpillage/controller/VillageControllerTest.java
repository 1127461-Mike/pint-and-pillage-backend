package nl.duckstudios.pintandpillage.controller;

import nl.duckstudios.pintandpillage.dao.VillageDAO;
import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.User;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.WorldMap;
import nl.duckstudios.pintandpillage.helper.VillageFactory;
import nl.duckstudios.pintandpillage.service.AccountService;
import nl.duckstudios.pintandpillage.service.AuthenticationService;
import nl.duckstudios.pintandpillage.service.VillageService;
import nl.duckstudios.pintandpillage.service.WorldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VillageControllerTest {

    @Mock
    private VillageFactory villageFactory;
    @Mock
    private VillageDAO villageDAO;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private AccountService accountService;
    @Mock
    private VillageService villageService;
    @Mock
    private WorldService worldService;
    @Mock
    private WorldMap worldMap;

    private VillageController villageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        villageController = new VillageController(
            villageFactory,
            villageDAO,
            authenticationService,
            accountService,
            villageService,
            worldService
        );
    }

    @Test
    void createVillage_shouldCreateAndSaveNewVillage() {
        User mockUser = new User();
        mockUser.setId(1L);
        Coord mockCoord = new Coord(1, 1);
        Village mockVillage = new Village();
        mockVillage.setVillageId(1L);
        mockVillage.setName("Test Village");
        mockVillage.setUser(mockUser);
        mockVillage.setPositionX(mockCoord.getX());
        mockVillage.setPositionY(mockCoord.getY());

        when(worldService.getWorldMap()).thenReturn(worldMap);
        when(worldMap.findEmptySpot()).thenReturn(mockCoord);
        when(authenticationService.getAuthenticatedUser()).thenReturn(mockUser);
        when(villageFactory.createBasicVillage(mockUser, mockCoord)).thenReturn(mockVillage);
        when(villageDAO.save(any(Village.class))).thenReturn(mockVillage);

        Village result = villageController.createVillage();

        assertNotNull(result);
        assertEquals(1L, result.getVillageId());
        assertEquals("Test Village", result.getName());
        assertEquals(mockUser, result.getUser());
        assertEquals(mockCoord.getX(), result.getPositionX());
        assertEquals(mockCoord.getY(), result.getPositionY());

        verify(authenticationService).getAuthenticatedUser();
        verify(worldService).getWorldMap();
        verify(worldMap).findEmptySpot();
        verify(villageFactory).createBasicVillage(mockUser, mockCoord);
        verify(villageDAO).save(mockVillage);
    }

    @Test
    void createVillage_shouldHandleNullUser() {
        when(authenticationService.getAuthenticatedUser()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            villageController.createVillage();
        });
    }

}