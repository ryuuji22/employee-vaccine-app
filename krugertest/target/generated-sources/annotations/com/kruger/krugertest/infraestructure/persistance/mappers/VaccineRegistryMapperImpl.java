package com.kruger.krugertest.infraestructure.persistance.mappers;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.entities.Role;
import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.domain.entities.VaccineRegistry;
import com.kruger.krugertest.infraestructure.models.EmployeeModel;
import com.kruger.krugertest.infraestructure.models.RoleModel;
import com.kruger.krugertest.infraestructure.models.UserModel;
import com.kruger.krugertest.infraestructure.models.VaccineRegistryModel;
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
public class VaccineRegistryMapperImpl implements VaccineRegistryMapper {

    @Autowired
    private VaccineMapper vaccineMapper;

    @Override
    public VaccineRegistry toVaccineRegistry(VaccineRegistryModel vaccineRegistryModel) {
        if ( vaccineRegistryModel == null ) {
            return null;
        }

        VaccineRegistry vaccineRegistry = new VaccineRegistry();

        vaccineRegistry.setEmployee( employeeModelToEmployee( vaccineRegistryModel.getEmployee() ) );
        vaccineRegistry.setId( vaccineRegistryModel.getId() );
        vaccineRegistry.setVaccinationDate( vaccineRegistryModel.getVaccinationDate() );
        vaccineRegistry.setDose( vaccineRegistryModel.getDose() );
        vaccineRegistry.setVaccine( vaccineMapper.toVaccine( vaccineRegistryModel.getVaccine() ) );
        vaccineRegistry.setEnabled( vaccineRegistryModel.getEnabled() );

        return vaccineRegistry;
    }

    @Override
    public List<VaccineRegistry> toVaccineRegistries(List<VaccineRegistryModel> vaccineRegistries) {
        if ( vaccineRegistries == null ) {
            return null;
        }

        List<VaccineRegistry> list = new ArrayList<VaccineRegistry>( vaccineRegistries.size() );
        for ( VaccineRegistryModel vaccineRegistryModel : vaccineRegistries ) {
            list.add( toVaccineRegistry( vaccineRegistryModel ) );
        }

        return list;
    }

    @Override
    public VaccineRegistryModel toVaccineRegistryModel(VaccineRegistry vaccineRegistry) {
        if ( vaccineRegistry == null ) {
            return null;
        }

        VaccineRegistryModel vaccineRegistryModel = new VaccineRegistryModel();

        vaccineRegistryModel.setEmployee( employeeToEmployeeModel( vaccineRegistry.getEmployee() ) );
        vaccineRegistryModel.setId( vaccineRegistry.getId() );
        vaccineRegistryModel.setVaccinationDate( vaccineRegistry.getVaccinationDate() );
        vaccineRegistryModel.setDose( vaccineRegistry.getDose() );
        vaccineRegistryModel.setVaccine( vaccineMapper.toVaccineModel( vaccineRegistry.getVaccine() ) );
        vaccineRegistryModel.setEnabled( vaccineRegistry.getEnabled() );

        return vaccineRegistryModel;
    }

    protected Role roleModelToRole(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleModel.getId() );
        role.setName( roleModel.getName() );

        return role;
    }

    protected Collection<Role> roleModelCollectionToRoleCollection(Collection<RoleModel> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<Role> collection1 = new ArrayList<Role>( collection.size() );
        for ( RoleModel roleModel : collection ) {
            collection1.add( roleModelToRole( roleModel ) );
        }

        return collection1;
    }

    protected User userModelToUser(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userModel.getId() );
        user.setIdentification( userModel.getIdentification() );
        user.setPassword( userModel.getPassword() );
        user.setEnabled( userModel.getEnabled() );
        user.setCreatedAt( userModel.getCreatedAt() );
        user.setRoles( roleModelCollectionToRoleCollection( userModel.getRoles() ) );

        return user;
    }

    protected Employee employeeModelToEmployee(EmployeeModel employeeModel) {
        if ( employeeModel == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeModel.getId() );
        employee.setIdentification( employeeModel.getIdentification() );
        employee.setFirstName( employeeModel.getFirstName() );
        employee.setLastName( employeeModel.getLastName() );
        employee.setEmail( employeeModel.getEmail() );
        employee.setAddress( employeeModel.getAddress() );
        employee.setPhone( employeeModel.getPhone() );
        employee.setBirthday( employeeModel.getBirthday() );
        employee.setVaccinated( employeeModel.getVaccinated() );
        employee.setEnabled( employeeModel.getEnabled() );
        employee.setUser( userModelToUser( employeeModel.getUser() ) );

        return employee;
    }

    protected RoleModel roleToRoleModel(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setId( role.getId() );
        roleModel.setName( role.getName() );

        return roleModel;
    }

    protected Collection<RoleModel> roleCollectionToRoleModelCollection(Collection<Role> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<RoleModel> collection1 = new ArrayList<RoleModel>( collection.size() );
        for ( Role role : collection ) {
            collection1.add( roleToRoleModel( role ) );
        }

        return collection1;
    }

    protected UserModel userToUserModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( user.getId() );
        userModel.setIdentification( user.getIdentification() );
        userModel.setPassword( user.getPassword() );
        userModel.setEnabled( user.getEnabled() );
        userModel.setCreatedAt( user.getCreatedAt() );
        userModel.setRoles( roleCollectionToRoleModelCollection( user.getRoles() ) );

        return userModel;
    }

    protected EmployeeModel employeeToEmployeeModel(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeModel employeeModel = new EmployeeModel();

        employeeModel.setId( employee.getId() );
        employeeModel.setIdentification( employee.getIdentification() );
        employeeModel.setFirstName( employee.getFirstName() );
        employeeModel.setLastName( employee.getLastName() );
        employeeModel.setEmail( employee.getEmail() );
        employeeModel.setAddress( employee.getAddress() );
        employeeModel.setPhone( employee.getPhone() );
        employeeModel.setBirthday( employee.getBirthday() );
        employeeModel.setVaccinated( employee.getVaccinated() );
        employeeModel.setEnabled( employee.getEnabled() );
        employeeModel.setUser( userToUserModel( employee.getUser() ) );

        return employeeModel;
    }
}
