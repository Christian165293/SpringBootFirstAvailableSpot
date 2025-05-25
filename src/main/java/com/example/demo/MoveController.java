package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MoveController {

    @GetMapping("/computer")
    public Space getTakenSpaces(@RequestParam(required = false) List<Integer> spaces) {
        return determineMove(spaces);
    }

    public Space determineMove(List<Integer> inTakenSpaces) {
        if (inTakenSpaces == null) {// /computer -> No board data provided
            return new Space(null);
        } else if (inTakenSpaces.isEmpty()) {// /computer?spaces -> Empty board - first move goes to space 1
            return new Space(1);
        } else if (inTakenSpaces.contains(1) && inTakenSpaces.size() == 1) {// /computer?spaces=1 -> Board with only space 1 marked - move to space 2
            return new Space(2);
        } else if (inTakenSpaces.containsAll(Arrays.asList(1, 3, 7)) && inTakenSpaces.size() == 3) {// /computer?spaces=1,3,7 -> Board with spaces 1, 3, and 7 marked - move to space 2
            return new Space(2);
        } else {//Default case
            return new Space(444);
        }
    }

}
