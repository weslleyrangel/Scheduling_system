package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.ServiceId;
import com.project.Scheduling_System.core.value_object.TenantId;

import java.math.BigDecimal;

public class Service {
    private final ServiceId id;
    private final TenantId tenantId;
    private final String name;
    private final BigDecimal price;
    private final int durationMinutes;

    public Service(TenantId tenantId,String name, BigDecimal price, int durationMinutes){
        validate(tenantId, name, price, durationMinutes);
        this.id = ServiceId.generateNew();
        this.tenantId = tenantId;
        this.name = name;
        this.price = price;
        this.durationMinutes = durationMinutes;
    }

    private void validate(TenantId tenantId, String name, BigDecimal price, int durationMinutes){
        if (tenantId == null) {
            throw DomainExceptionFactory.mandatoryField("TenantId");
        }
        if(name == null || name.trim().isEmpty()){
            throw DomainExceptionFactory.mandatoryField("Nome do Serviço");
        }
        if(price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw DomainExceptionFactory.businessRuleViolated("O preço do serviço deve ser maior que zero");
        }
        if(durationMinutes < 15){
            throw DomainExceptionFactory.businessRuleViolated("A duração do serviço deve ser no mínimo 15 minutos");
        }
    }

    public TenantId getTenantId(){return tenantId;}
    public ServiceId getId(){return id;}
    public String getName(){return name;}
    public BigDecimal getPrice(){return price;}
    public int getDurationMinutes(){return durationMinutes;}
}
