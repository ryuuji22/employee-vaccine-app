package com.kruger.krugertest.infraestructure.persistance.mappers;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.entities.VaccineRegistry;
import com.kruger.krugertest.infraestructure.models.EmployeeModel;
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
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VaccineRegistryMapper vaccineRegistryMapper;

    @Override
    public Employee toEmployee(EmployeeModel employeeModel) {
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
        employee.setUser( userMapper.toUser( employeeModel.getUser() ) );
        employee.setRegistry( vaccineRegistryModelCollectionToVaccineRegistryCollection( employeeModel.getVaccineRegistry() ) );

        return employee;
    }

    @Override
    public List<Employee> toEmployees(List<EmployeeModel> employees) {
        if ( employees == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employees.size() );
        for ( EmployeeModel employeeModel : employees ) {
            list.add( toEmployee( employeeModel ) );
        }

        return list;
    }

    @Override
    public EmployeeModel toEmployeeModel(Employee employee) {
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
        employeeModel.setUser( userMapper.toUserModel( employee.getUser() ) );
        employeeModel.setVaccineRegistry( vaccineRegistryCollectionToVaccineRegistryModelCollection( employee.getRegistry() ) );

        return employeeModel;
    }

    protected Collection<VaccineRegistry> vaccineRegistryModelCollectionToVaccineRegistryCollection(Collection<VaccineRegistryModel> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<VaccineRegistry> collection1 = new ArrayList<VaccineRegistry>( collection.size() );
        for ( VaccineRegistryModel vaccineRegistryModel : collection ) {
            collection1.add( vaccineRegistryMapper.toVaccineRegistry( vaccineRegistryModel ) );
        }

        return collection1;
    }

    protected Collection<VaccineRegistryModel> vaccineRegistryCollectionToVaccineRegistryModelCollection(Collection<VaccineRegistry> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<VaccineRegistryModel> collection1 = new ArrayList<VaccineRegistryModel>( collection.size() );
        for ( VaccineRegistry vaccineRegistry : collection ) {
            collection1.add( vaccineRegistryMapper.toVaccineRegistryModel( vaccineRegistry ) );
        }

        return collection1;
    }
}
