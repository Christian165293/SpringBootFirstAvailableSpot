package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveControllerTest {
    private MoveController moveController;

    @BeforeEach
    void setUp() {
        moveController = new MoveController();
    }

    @Test
    @DisplayName("Return Space with null when input is null")
    void testDetermineMoveWithNullInput() {
        List<Integer> takenSpaces = null;
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertNull(result.getSpace());
    }

    @Test
    @DisplayName("Return Space(1) when board is empty")
    void testDetermineMoveWithEmptyBoard() {
        List<Integer> takenSpaces = Collections.emptyList();
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(1, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(2) when only space 1 is taken")
    void testDetermineMoveWithOnlySpace1Taken() {
        List<Integer> takenSpaces = Arrays.asList(1);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(2, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(2) when spaces 1, 3, and 7 are taken")
    void testDetermineMoveWithSpaces1_3_7Taken() {
        List<Integer> takenSpaces = Arrays.asList(1, 3, 7);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(2, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(2) when spaces 1, 3, and 7 are taken in different order")
    void testDetermineMoveWithSpaces1_3_7TakenDifferentOrder() {
        List<Integer> takenSpaces = Arrays.asList(7, 1, 3);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(2, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(444) for default case - multiple spaces but not matching special cases")
    void testDetermineMoveDefaultCase() {
        List<Integer> takenSpaces = Arrays.asList(2, 4, 6);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(444, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(444) when space 1 is taken with other spaces (not just space 1 alone)")
    void testDetermineMoveSpace1WithOthers() {
        List<Integer> takenSpaces = Arrays.asList(1, 2);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(444, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(444) when spaces 1, 3, 7 are taken with additional spaces")
    void testDetermineMoveSpaces1_3_7WithAdditional() {
        List<Integer> takenSpaces = Arrays.asList(1, 3, 7, 9);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(444, result.getSpace());
    }

    @Test
    @DisplayName("Return Space(444) when only spaces 1 and 3 are taken (missing 7)")
    void testDetermineMoveSpaces1_3Only() {
        List<Integer> takenSpaces = Arrays.asList(1, 3);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(444, result.getSpace());
    }

    @Test
    @DisplayName("Handle single space other than 1")
    void testDetermineMoveSingleSpaceNotOne() {
        List<Integer> takenSpaces = Arrays.asList(5);
        Space result = moveController.determineMove(takenSpaces);
        assertNotNull(result);
        assertEquals(444, result.getSpace());
    }

}