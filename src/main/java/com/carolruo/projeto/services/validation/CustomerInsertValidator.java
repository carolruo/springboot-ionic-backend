package com.carolruo.projeto.services.validation;

import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.domain.enums.CustomerType;
import com.carolruo.projeto.dto.CustomerNewDTO;
import com.carolruo.projeto.repositories.CustomerRepository;
import com.carolruo.projeto.resources.exceptions.FieldMessage;
import com.carolruo.projeto.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void initialize(CustomerInsert ann) {
    }

    @Override
    public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getCustomerTypeId().equals(CustomerType.INDIVIDUAL_PERSON.getId()) && !BR.isValidCpf(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));
        }
        if (objDto.getCustomerTypeId().equals(CustomerType.LEGAL_PERSON.getId()) && !BR.isValidCnpj(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
        }

        Customer aux = repository.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existnte"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}