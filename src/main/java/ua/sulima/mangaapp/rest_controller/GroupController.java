package ua.sulima.mangaapp.rest_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.sulima.mangaapp.domain.Group;
import ua.sulima.mangaapp.dto.group.GroupDTO;
import ua.sulima.mangaapp.dto.group.GroupToCreateDTO;
import ua.sulima.mangaapp.service.GroupService;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Group> showCreatorById(@PathVariable("id") Long id){
        log.info("Requesting for creator with id -> {}", id);
        Group group = groupService.findById(id);
        return ResponseEntity.ok(group);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<GroupDTO> updateGroup(
            @PathVariable("id") Long id,
            @RequestBody GroupToCreateDTO groupToUpdateDto){
        log.info("Requesting for creator with id -> {}", id);
        GroupDTO updatedGroupDto =
                groupService.partialUpdateGroup(groupToUpdateDto, id);
        return ResponseEntity.ok(updatedGroupDto);
    }

    @PostMapping("/new")
    public ResponseEntity<GroupDTO> createGroup(
            @RequestBody GroupToCreateDTO groupToCreateDto
    ){
        GroupDTO updatedGroupDto = groupService.createGroup(groupToCreateDto);
        URI newCreatorLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedGroupDto.getId())
                .toUri();
        return ResponseEntity.created(newCreatorLocation).build();
    }
}
