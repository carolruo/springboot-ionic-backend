package com.carolruo.projeto.services.validation;

import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.domain.enums.CustomerType;
import com.carolruo.projeto.dto.CustomerDTO;
import com.carolruo.projeto.dto.CustomerNewDTO;
import com.carolruo.projeto.repositories.CustomerRepository;
import com.carolruo.projeto.resources.exceptions.FieldMessage;
import com.carolruo.projeto.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
    private HttpServletRequest request; //obter parametro da uri (id do cliente)
    @Autowired
    private CustomerRepository repository;

    @Override
    public void initialize(CustomerUpdate ann) {
    }

    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {

        //Uma requisicao tem varios atributos, esses atributos estao armazenados dentro de um map
        @SuppressWarnings("unchecked")
        Map<String, String> mapDaRequisicao = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(mapDaRequisicao.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Customer aux = repository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existnte em outro cliente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}