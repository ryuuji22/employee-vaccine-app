package com.kruger.krugertest.infraestructure.persistance.mappers;

import com.kruger.krugertest.domain.entities.Role;
import com.kruger.krugertest.infraestructure.models.RoleModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-08T14:07:22-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toRole(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleModel.getId() );
        role.setName( roleModel.getName() );

        return role;
    }

    @Override
    public List<Role> toRoles(List<RoleModel> roles) {
        if ( roles == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( roles.size() );
        for ( RoleModel roleModel : roles ) {
            list.add( toRole( roleModel ) );
        }

        return list;
    }

    @Override
    public RoleModel toRoleModel(Role roles) {
        if ( roles == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setId( roles.getId() );
        roleModel.setName( roles.getName() );

        return roleModel;
    }
}
