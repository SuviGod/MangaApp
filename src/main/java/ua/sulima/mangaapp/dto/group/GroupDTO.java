package ua.sulima.mangaapp.dto.group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GroupDTO {
    private Integer id;

    private String groupName;

    private String link1;

    private String link2;

}
