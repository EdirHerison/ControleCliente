package model.entities.utils;

import java.util.Date;

import model.entidades.Venda;
import model.entities.enums.Status;

public class VerificaStatus {
	
	public Status verStatus(Date dt_compr, Venda vd) {
		
		
		Status stcli = null;
		
		Date dt_atu = new Date();
		long diff = dt_atu.getTime() - dt_compr.getTime();
		long diffDias = diff / (24 * 60 * 60 * 1000);
		
		if(diffDias >= 90) {
			return stcli.INATIVO;
		}else if (diffDias <= 89 && diffDias >= 31) {
			return stcli.PRE_INATIVO;
		}else {
			return stcli.ATIVO;
		}
		
	}

}
