package com.oose.jobportal.models.mappers;

import com.oose.jobportal.models.entities.Role;
import com.oose.jobportal.models.entities.User;
import com.oose.jobportal.models.entities.UserDetail;

public class UserDetailMapper {
    public static UserDetail mappingToUserDetail(User user) {
        UserDetail userDetail = new UserDetail();

        userDetail.setId(user.getUserID());
        userDetail.setGmail(user.getGmail());
        userDetail.setPassword(user.getPassword());
        userDetail.setName(user.getName());
//        didn't have set Role for user detail
        if (user.getRole() == 2) {
            userDetail.setRole(Role.ADMIN);
        } else {
            userDetail.setRole(Role.USER);
        }
        return userDetail;
    }

    public static User mappingToUser(UserDetail userDetail) {
        User user = new User();

        user.setGmail(userDetail.getGmail());
        user.setPassword(userDetail.getPassword());
        user.setName(userDetail.getName());
        if(userDetail.getRole() == Role.USER) {
            user.setRole(1);
        }
        if(userDetail.getRole() == Role.ADMIN) {
            user.setRole(2);
        }

        return user;
    }
}
