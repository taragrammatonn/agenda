package com.flux.services.telegram.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVO {

    public UserVO(Long chatId, String fName, String lName) {
        this.chatId = chatId;
        this.fName = fName;
        this.lName = lName;
    }

    public UserVO(Long chatId, String userGroup) {
        this.chatId = chatId;
        this.userGroup = userGroup;
    }

    Long chatId;
    String fName;
    String lName;
    String userNickName;
    String userGroup;
    String userLanguage;
    Boolean active;
    Boolean adminEntity;
    Boolean isDefined;

}
