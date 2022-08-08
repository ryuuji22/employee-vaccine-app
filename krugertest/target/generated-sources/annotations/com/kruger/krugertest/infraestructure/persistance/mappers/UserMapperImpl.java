package com.kruger.krugertest.infraestructure.persistance.mappers;

import com.kruger.krugertest.domain.entities.Role;
import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.infraestructure.models.RoleModel;
import com.kruger.krugertest.infraestructure.models.UserModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-08T14:07:22-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User toUser(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userModel.getId() );
        user.setIdentification( userModel.getIdentification() );
        user.setPassword( userModel.getPassword() );
        user.setEnabled( userModel.getEnabled() );
        user.setRoles( roleModelCollectionToRoleCollection( userModel.getRoles() ) );
        user.setCreatedAt( userModel.getCreatedAt() );

        return user;
    }

    @Override
    public List<User> toUsers(List<UserModel> users) {
        if ( users == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( users.size() );
        for ( UserModel userModel : users ) {
            list.add( toUser( userModel ) );
        }

        return list;
    }

    @Override
    public UserModel toUserModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( user.getId() );
        userModel.setIdentification( user.getIdentification() );
        userModel.setPassword( user.getPassword() );
        userModel.setEnabled( user.getEnabled() );
        userModel.setRoles( roleCollectionToRoleModelCollection( user.getRoles() ) );
        userModel.setCreatedAt( user.getCreatedAt() );

        return userModel;
    }

    protected Collection<Role> roleModelCollectionToRoleCollection(Collection<RoleModel> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<Role> collection1 = new ArrayList<Role>( collection.size() );
        for ( RoleModel roleModel : collection ) {
            collection1.add( roleMapper.toRole( roleModel ) );
        }

        return collection1;
    }

    protected Collection<RoleModel> roleCollectionToRoleModelCollection(Collection<Role> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<RoleModel> collection1 = new ArrayList<RoleModel>( collection.size() );
        for ( Role role : collection ) {
            collection1.add( roleMapper.toRoleModel( role ) );
        }

        return collection1;
    }
}
