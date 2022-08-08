package com.kruger.krugertest.infraestructure.persistance.mappers;

import com.kruger.krugertest.domain.entities.Vaccine;
import com.kruger.krugertest.infraestructure.models.VaccineModel;
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
public class VaccineMapperImpl implements VaccineMapper {

    @Override
    public Vaccine toVaccine(VaccineModel vaccineModel) {
        if ( vaccineModel == null ) {
            return null;
        }

        Vaccine vaccine = new Vaccine();

        vaccine.setId( vaccineModel.getId() );
        vaccine.setName( vaccineModel.getName() );

        return vaccine;
    }

    @Override
    public List<Vaccine> toVaccines(List<VaccineModel> vaccines) {
        if ( vaccines == null ) {
            return null;
        }

        List<Vaccine> list = new ArrayList<Vaccine>( vaccines.size() );
        for ( VaccineModel vaccineModel : vaccines ) {
            list.add( toVaccine( vaccineModel ) );
        }

        return list;
    }

    @Override
    public VaccineModel toVaccineModel(Vaccine vaccines) {
        if ( vaccines == null ) {
            return null;
        }

        VaccineModel vaccineModel = new VaccineModel();

        vaccineModel.setId( vaccines.getId() );
        vaccineModel.setName( vaccines.getName() );

        return vaccineModel;
    }
}
