package ua.sulima.mangaapp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.sulima.mangaapp.domain.Group;
import ua.sulima.mangaapp.dto.group.GroupDTO;
import ua.sulima.mangaapp.dto.group.GroupMapper;
import ua.sulima.mangaapp.dto.group.GroupToCreateDTO;
import ua.sulima.mangaapp.exception.rest.CustomNotFoundException;
import ua.sulima.mangaapp.repository.GroupRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    private final ModelMapper modelMapper;
    public GroupDTO createGroup(GroupToCreateDTO groupToCreateDto){
        Group groupToCreate = modelMapper.map(groupToCreateDto, Group.class);
        groupRepository.save(groupToCreate);
        return modelMapper.map(groupToCreate, GroupDTO.class);
    }

    public GroupDTO partialUpdateGroup(GroupToCreateDTO groupToUpdateDto, Long groupId){
        var maybeGroupToBeUpdated = groupRepository.findById(groupId);
        if (maybeGroupToBeUpdated.isEmpty()){
            throw new CustomNotFoundException("Group is not found");
        }
        groupMapper.updateCustomerFromDto(
                groupToUpdateDto,
                maybeGroupToBeUpdated.get());
        return modelMapper.map(maybeGroupToBeUpdated.get(), GroupDTO.class);
    }

    @Transactional(readOnly = true)
    public Group findById(Long id){
        var maybeGroup = groupRepository.findById(id);
        if (maybeGroup.isEmpty()){
            throw new CustomNotFoundException("Group is not found");
        }

        return maybeGroup.get();
    }
}
