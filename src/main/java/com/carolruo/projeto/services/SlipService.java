package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.PaymentSlip;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SlipService {

    //Numa situacao real seria chamado um WebService de gerar boleto
    public void fillInSlipDate(PaymentSlip slip, Date instant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        slip.setDueDate(cal.getTime());
    }
}
